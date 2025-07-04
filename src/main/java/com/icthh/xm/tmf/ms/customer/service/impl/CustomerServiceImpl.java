package com.icthh.xm.tmf.ms.customer.service.impl;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import com.google.common.annotations.VisibleForTesting;
import com.icthh.xm.commons.exceptions.BusinessException;
import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import com.icthh.xm.tmf.ms.customer.mapper.CustomerCharacteristicMapper;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import com.icthh.xm.tmf.ms.customer.repository.CustomerCharacteristicRepository;
import com.icthh.xm.tmf.ms.customer.service.CustomerConfigurationService;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerCharacteristicMapper mapper;
    private final CustomerConfigurationService customerConfigurationService;
    private final CustomerCharacteristicRepository customerCharacteristicRepository;

    @Override
    public List<Customer> getCustomer(String id, String profile, String fields) {
        Collection<String> fieldsList = ofNullable(fields)
            .map(f -> f.split(",", -1))
            .map(Arrays::asList)
            .orElse(emptyList());
        return toCustomers(fieldsList,
            customerCharacteristicRepository.findAllByCustomerIdAndKeyIn(id, fieldsList)
        );
    }

    @Override
    public List<Customer> getCustomerBySpecification(Specification<CustomerCharacteristicEntity> spec) {
        return customerCharacteristicRepository.findAll(spec).stream()
            .collect(Collectors.groupingBy(CustomerCharacteristicEntity::getCustomerId))
            .entrySet().stream()
            .map(e -> new Customer().name(e.getKey()).characteristic(mapper.toCharacteristics(e.getValue())))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Customer patchCustomer(String customerId, List<PatchOperation> patchOperations) {
        Customer customer = new Customer();

        for (PatchOperation update : patchOperations) {
            Characteristic characteristic = update.getValue();
            if (update.getPath().equals("/characteristic/-")) {
                CustomerCharacteristics.Characteristic config = validateCharacteristic(characteristic);

                switch (update.getOp()) {
                    case "add":
                    case "replace":
                        addOrReplace(customerId, characteristic, config);
                        break;

                    case "remove":
                        customerCharacteristicRepository.deleteByCustomerIdAndKey(customerId, characteristic.getName());
                        break;
                    default:
                        throw new UnsupportedOperationException("not able to process the operation " + update.getOp());
                }
            }
            customer.addCharacteristicItem(characteristic);
        }

        return customer;
    }

    private void addOrReplace(String customerId, Characteristic characteristic,
        CustomerCharacteristics.Characteristic config) {

        if (isDefaultValue(characteristic, config)) {
            log.debug("The new value is a default one, removing from the db if any");
            customerCharacteristicRepository.deleteByCustomerIdAndKey(customerId, characteristic.getName());
        } else {
            updateAttributeDatabase(customerId, characteristic);
        }
    }

    private boolean isDefaultValue(Characteristic characteristic,
        CustomerCharacteristics.Characteristic config) {
        return ofNullable(characteristic)
            .filter(ch -> Optional.ofNullable(config.getDefaultValue())
                .map(def -> def.equals(characteristic.getValue())).orElse(false))
            .isPresent();
    }

    void updateAttributeDatabase(String id, Characteristic characteristic) {
        customerCharacteristicRepository.findByCustomerIdAndKey(id, characteristic.getName())
            .map(characteristicEntity -> updateAttributes(characteristic, characteristicEntity))
            .orElseGet(() -> saveNewAttribute(id, characteristic));
    }

    private CustomerCharacteristicEntity updateAttributes(Characteristic characteristic,
        CustomerCharacteristicEntity entity) {
        entity.setKey(characteristic.getName());
        entity.setValue(characteristic.getValue());
        return entity;
    }

    private CustomerCharacteristicEntity saveNewAttribute(String id, Characteristic characteristic) {
        return customerCharacteristicRepository.save(mapper.toAttribute(id, characteristic));
    }

    @VisibleForTesting
    CustomerCharacteristics.Characteristic validateCharacteristic(Characteristic characteristic) {
        return customerConfigurationService.getConfig().getCharacteristics().stream()
            .filter(config -> ofNullable(config.getKeyRegexp())
                .map(reg -> characteristic.getName().matches(reg)).orElse(false)
            ).findAny()
            .filter(config -> fitConstraints(characteristic, config))
            .orElseThrow(() -> {
                throw new BusinessException("error.characteristic.invalid", "Invalid characteristic "
                    + characteristic.getName());
            });
    }

    private boolean fitConstraints(Characteristic characteristic,
        CustomerCharacteristics.Characteristic characteristicConfig) {
        return Optional.of(characteristicConfig)
            .filter(config -> isInPredefinedValues(config.getPredefinedValues(), characteristic.getValue()))
            .filter(config -> isFitRegExp(config.getRegexp(), characteristic.getValue()))
            .filter(config -> isFitMaxLength(config.getMaxLength(), characteristic.getValue()))
            .filter(config -> isFitMinLength(config.getMinLength(), characteristic.getValue()))
            .isPresent();
    }

    private boolean isFitMinLength(Integer minLength, String value) {
        if (minLength == null) {
            return true;
        } else {
            return value != null && value.length() >= minLength;
        }
    }

    private boolean isFitMaxLength(Integer maxLength, String value) {
        if (maxLength == null) {
            return true;
        } else {
            return value != null && value.length() <= maxLength;
        }
    }

    private boolean isFitRegExp(String regexp, String value) {
        if (isNull(regexp)) {
            return true;
        } else {
            return value != null && value.matches(regexp);
        }
    }

    private boolean isInPredefinedValues(List<String> predefinedValues, String characteristicValue) {
        if (CollectionUtils.isEmpty(predefinedValues)) {
            log.debug("No predefined values configured, allowing all");
            return true;
        } else {
            return predefinedValues.contains(characteristicValue);
        }
    }

    private List<Customer> toCustomers(Collection<String> requestedFields,
        Collection<CustomerCharacteristicEntity> foundFields) {

        Collection<CustomerCharacteristicEntity> withDefaultValues =
            fillDefaultValues(requestedFields, foundFields);

        return List.of(new Customer().characteristic(mapper.toCharacteristics(withDefaultValues)));
    }

    private Collection<CustomerCharacteristicEntity> fillDefaultValues(
        Collection<String> requestedFields,
        Collection<CustomerCharacteristicEntity> characteristicEntities) {

        Set<String> foundFields = characteristicEntities.stream()
            .map(CustomerCharacteristicEntity::getKey)
            .collect(toSet());

        requestedFields
            .forEach(requestedField -> addDefaultValue(foundFields, requestedField, characteristicEntities));

        return characteristicEntities;
    }

    private void addDefaultValue(Set<String> foundFields, String requestedField,
        Collection<CustomerCharacteristicEntity> characteristicEntities) {

        if (!foundFields.contains(requestedField)) {
            createDefaultValue(characteristicEntities, requestedField);
        }
    }

    private void createDefaultValue(Collection<CustomerCharacteristicEntity> characteristicEntities,
        String requestedField) {
        ofNullable(getDefaultValue(requestedField))
            .ifPresent(defaultValue -> characteristicEntities.add(
                mapper.toNewCharacteristic(requestedField, defaultValue)));
    }

    private String getDefaultValue(String requestedField) {
        return customerConfigurationService.getConfig().getCharacteristics().stream()
            .filter(characteristic -> characteristic.getKeyRegexp() != null
                && characteristic.getKeyRegexp().matches(requestedField))
            .findAny()
            .map(CustomerCharacteristics.Characteristic::getDefaultValue)
            .orElse(null);
    }
}

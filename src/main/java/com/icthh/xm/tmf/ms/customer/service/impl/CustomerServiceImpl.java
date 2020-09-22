package com.icthh.xm.tmf.ms.customer.service.impl;

import static com.icthh.xm.tmf.ms.customer.util.StringUtil.toStringList;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import com.icthh.xm.commons.exceptions.BusinessException;
import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import com.icthh.xm.tmf.ms.customer.mapper.CustomerCharacteristicMapper;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import com.icthh.xm.tmf.ms.customer.repository.CustomerCharacteristicRepository;
import com.icthh.xm.tmf.ms.customer.service.ConfigCustomerService;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import java.util.ArrayList;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl
    implements CustomerService {

    private final CustomerCharacteristicMapper mapper;
    private final ConfigCustomerService configCustomerService;
    private final CustomerCharacteristicRepository customerCharacteristicRepository;

    @Override
    //todo V: review the test and leave it if okay
    public List<Customer> getCustomer(String id, String profile, String fields) {
        Collection<String> fieldsList = toStringList(fields);
        return toCustomers(
            fieldsList,
            customerCharacteristicRepository.findAllByCustomerIdAndKeyIn(id, fieldsList)
        );
    }

    @Override
    public List<Customer> getCustomerFirebaseIds(List<String> ids) {   //todo V!: move to LEP
        Specification<CustomerCharacteristicEntity> spec = Specification.where(
            (Specification<CustomerCharacteristicEntity>) (root, query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.like(root.get("key"), "REGISTRATION-TOKEN%"),
                    root.get("customerId").in(ids))
        );

        List<CustomerCharacteristicEntity> all = customerCharacteristicRepository.findAll(spec);

        return all.stream()
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

                boolean defaultValue = isDefaultValue(characteristic, config);
                switch (update.getOp()) {
                    case "add":
                    case "replace":

                        if (defaultValue) {
                            log.debug("The new value is a default one, removing from the db if any");
                            customerCharacteristicRepository.deleteByCustomerIdAndKey(customerId, characteristic.getName());
                        } else {
                            updateAttributeDatabase(customerId, characteristic);
                        }
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

    private boolean isDefaultValue(Characteristic characteristic, CustomerCharacteristics.Characteristic config) {
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

    private CustomerCharacteristicEntity updateAttributes(
        Characteristic characteristic, CustomerCharacteristicEntity entity
    ) {
        entity.setKey(characteristic.getName());
        entity.setValue(characteristic.getValue());
        return entity;
    }

    private CustomerCharacteristicEntity saveNewAttribute(String id, Characteristic characteristic) {
        return customerCharacteristicRepository.save(mapper.toAttribute(id, characteristic));
    }

    private CustomerCharacteristics.Characteristic validateCharacteristic(Characteristic characteristic) {

        CustomerCharacteristics.Characteristic characteristicConfig = configCustomerService.getConfig().getCharacteristics().stream()
            .filter(config ->
                Optional.ofNullable(config.getKey()).map(key -> key.equals(characteristic.getName())).orElse(false)
                    ||
                    Optional.ofNullable(config.getKeyRegexp())
                        .map(reg -> characteristic.getName().matches(reg)).orElse(false)
            ).findAny()
            .orElseThrow(() -> {
                log.debug("Requested characteristic is missing from the configuration {}", characteristic.getName());
                //todo V: use correct exception response from commons module - not it's mapped to 500 via ErrorVM
                throw new BusinessException("code //todo V: set correct value  and add it to ", "Invalid characteristic " + characteristic.getName());
            });


        boolean fitConstraints = fitConstraints(characteristic, characteristicConfig); //todo V: rewrite?
        if (!fitConstraints) { //todo V!: remove duplication
            log.debug("Requested characteristic is missing from the configuration {}", characteristic.getName());
            //todo V: use correct exception response from commons module - not it's mapped to 500 via ErrorVM
            throw new BusinessException("code //todo V: set correct value and add it to ", "Invalid characteristic " + characteristic.getName());
        }

        return characteristicConfig;
    }

    private boolean fitConstraints(Characteristic characteristic, CustomerCharacteristics.Characteristic characteristicConfig) {
        return Optional.of(characteristicConfig)
            .filter(predefined -> isInPredefinedValues(predefined.getPredefinedValues(), characteristic.getValue()))
            .filter(predefined -> isFitRegExp(predefined.getRegexp(), characteristic.getValue()))
            .filter(predefined -> isFitLengthy(predefined.getMax(), characteristic.getValue()))
            .filter(predefined -> isFitLengthy(predefined.getMin(), characteristic.getValue()))
            .isPresent();
    }

    private boolean isFitRegExp(String regexp, String value) {
        if (isNull(regexp))
            return true;
        else
            return value.matches(regexp);
    }

    private boolean isInPredefinedValues(List<String> predefinedValues, String characteristicValue) {
        log.info("Predefined values {} and characteristic value {} ", predefinedValues, characteristicValue);

        if (isNull(predefinedValues) || predefinedValues.isEmpty())
            return true;

        return predefinedValues.contains(characteristicValue);
    }

    private boolean isFitLengthy(Integer requiredLengthy, String characteristic) {
        log.info("Required lengthy {} characteristic {} ", requiredLengthy, characteristic);

        if (isNull(requiredLengthy))
            return true;
        else
            return characteristic.length() <= requiredLengthy;
    }

    private List<Customer> toCustomers(
        Collection<String> requestedFields, Collection<CustomerCharacteristicEntity> foundFields
    ) {
        Collection<CustomerCharacteristicEntity> withDefaultValues =
            fillDefaultValues(requestedFields, foundFields);

        List<Customer> customers = new ArrayList<>();

        Customer customer = new Customer();
        customer.setCharacteristic(mapper.toCharacteristics(withDefaultValues));

        customers.add(customer);

        return customers;
    }

    private Collection<CustomerCharacteristicEntity> fillDefaultValues(
        Collection<String> requestedFields, Collection<CustomerCharacteristicEntity> characteristicEntities
    ) {
        Set<String> foundFields = characteristicEntities.stream()
            .map(CustomerCharacteristicEntity::getKey)
            .collect(toSet());

        requestedFields
            .forEach(requestedField -> addDefaultValue(foundFields, requestedField, characteristicEntities));

        return characteristicEntities;
    }

    private void addDefaultValue(
        Set<String> foundFields,
        String requestedField,
        Collection<CustomerCharacteristicEntity> characteristicEntities
    ) {
        if (!foundFields.contains(requestedField))
            createDefaultValue(characteristicEntities, requestedField);
    }

    private void createDefaultValue(
        Collection<CustomerCharacteristicEntity> characteristicEntities, String requestedField
    ) {
        ofNullable(getDefaultValue(requestedField))
            .ifPresent(
                defaultValue -> characteristicEntities.add(mapper.toNewCharacteristic(requestedField, defaultValue))
            );
    }

    private String getDefaultValue(String requestedField) {
        return configCustomerService.getConfig().getCharacteristics().stream()
            .filter(characteristic -> characteristic.getKey().equals(requestedField)) //todo V: NPE warning
            .findAny()
            .map(CustomerCharacteristics.Characteristic::getDefaultValue)
            .orElse(null);
    }
}

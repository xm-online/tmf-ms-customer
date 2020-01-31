package com.icthh.xm.tmf.ms.customer.service.impl;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import com.icthh.xm.tmf.ms.customer.mapper.CustomerCharacteristicMapper;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.CustomerUpdate;
import com.icthh.xm.tmf.ms.customer.repository.CustomerEntityRepository;
import com.icthh.xm.tmf.ms.customer.service.ConfigCustomerService;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.CheckForNull;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.icthh.xm.tmf.ms.customer.util.StringUtil.toStringList;
import static java.lang.Long.parseLong;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerCharacteristicMapper mapper;
    private final ConfigCustomerService configCustomerService;
    private final CustomerEntityRepository customerEntityRepository;

    @Override
    public List<Customer> getCustomer(String id, String profile, String fields) {
        Collection<String> fieldsList = toStringList(fields);
        return toCustomers(
            fieldsList,
            customerEntityRepository.findAllByCustomerIdAndKeyIn(parseLong(id), fieldsList)
        );
    }

    @Override
    public Customer patchCustomer(String id, CustomerUpdate customerUpdate) {
        Customer customer = new Customer();

        customerUpdate.getCharacteristic()
            .forEach(characteristic -> updateCharacteristic(id, characteristic, customer));

        return customer;
    }

    private void updateCharacteristic(String id, Characteristic characteristic, Customer customer) {
        try {

            isCharacteristicValid(characteristic);

            if (isDefaultValue(characteristic.getDefault()))
                customerEntityRepository.findById(Long.parseLong(id))
                    .ifPresent(customerEntityRepository::delete);
            else
                updateAttributeDatabase(id, characteristic);

            customer.addCharacteristicItem(characteristic);

        } catch (Exception ex) {
            log.error("Could not update characteristic : " + ex.getMessage(), ex);
        }
    }

    private boolean isDefaultValue(@CheckForNull Boolean defaultFlag) {
        return ofNullable(defaultFlag)
            .filter(isDefault -> isDefault.equals(true))
            .isPresent();
    }

    @Transactional
    void updateAttributeDatabase(String id, Characteristic characteristic) {
        customerEntityRepository.findByCustomerIdAndKey(Long.parseLong(id), characteristic.getName())
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
        return customerEntityRepository.save(mapper.toAttribute(id, characteristic));
    }

    private void isCharacteristicValid(Characteristic characteristic) {

        boolean configHasRequestedKey = configCustomerService.getConfig().getCharacteristics().stream()
            .map(CustomerCharacteristics.Characteristic::getKey)
            .anyMatch(key -> key.equals(characteristic.getName()));

        boolean fitConstraints = fitConstraints(characteristic);

        if (!configHasRequestedKey || !fitConstraints)
            throw new IllegalArgumentException("Attribute is missing in property file : " + characteristic.getName());

    }

    private boolean fitConstraints(Characteristic characteristic) {
        return configCustomerService.getConfig().getCharacteristics().stream()
            .filter(config -> config.getKey().equals(characteristic.getName())).findAny()
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
            return characteristic.length() == requiredLengthy;
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
            .filter(characteristic -> characteristic.getKey().equals(requestedField))
            .findAny()
            .map(CustomerCharacteristics.Characteristic::getDefaultValue)
            .orElse(null);
    }
}

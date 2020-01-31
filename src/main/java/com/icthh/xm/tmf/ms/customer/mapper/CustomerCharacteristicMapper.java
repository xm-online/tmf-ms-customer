package com.icthh.xm.tmf.ms.customer.mapper;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

@Component
public class CustomerCharacteristicMapper {

    public List<Characteristic> toCharacteristics(Collection<CustomerCharacteristicEntity> customerEntity) {
        return customerEntity.stream()
            .map(this::toCharacteristic)
            .collect(toList());
    }

    public Characteristic toCharacteristic(CustomerCharacteristicEntity characteristicEntity) {
        Characteristic characteristic = new Characteristic();
        characteristic.setName(characteristicEntity.getKey());
        characteristic.setValue(characteristicEntity.getValue());
        return characteristic;
    }

    public CustomerCharacteristicEntity toNewCharacteristic(String requestedField, String defaultValue) {
        return CustomerCharacteristicEntity.builder()
            .key(requestedField)
            .value(defaultValue)
            .build();
    }

    public CustomerCharacteristicEntity toAttribute(String id, Characteristic characteristic) {
        return CustomerCharacteristicEntity.builder()
            .customerId(parseLong(id))
            .key(characteristic.getName())
            .value(characteristic.getValue())
            .build();
    }
}

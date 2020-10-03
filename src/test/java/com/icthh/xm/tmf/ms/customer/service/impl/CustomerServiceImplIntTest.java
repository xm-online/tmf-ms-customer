package com.icthh.xm.tmf.ms.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.icthh.xm.commons.exceptions.BusinessException;
import com.icthh.xm.tmf.ms.customer.CustomerApp;
import com.icthh.xm.tmf.ms.customer.config.SecurityBeanOverrideConfiguration;
import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity_;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import com.icthh.xm.tmf.ms.customer.repository.CustomerCharacteristicRepository;
import com.icthh.xm.tmf.ms.customer.service.CustomerConfigurationService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CustomerApp.class})
@Transactional
public class CustomerServiceImplIntTest {

    private static final String CUSTOMER_ID = "1";
    private static final String LANGUAGE = "language";
    private static final String UA = "ua";
    private static final String RU = "ru";
    private static final String EN = "en";

    private Characteristic characteristicValue = new Characteristic()
        .name(LANGUAGE)
        .value(RU);

    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CustomerCharacteristicRepository customerCharacteristicRepository;
    @MockBean
    private CustomerConfigurationService customerConfigurationService;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void setup() {
        Mockito.when(customerConfigurationService.getConfig()).thenReturn(new CustomerCharacteristics()
            .setCharacteristics(List.of(new CustomerCharacteristics.Characteristic()
                .setKeyRegexp(LANGUAGE)
                .setDefaultValue(UA)
                .setPredefinedValues(List.of(UA, RU, EN)))));
    }

    @Test
    void shouldAddCharacteristics() {
        //when
        customerService.patchCustomer(CUSTOMER_ID, List.of(new PatchOperation()
            .op("add")
            .path("/characteristic/-")
            .value(characteristicValue)
        ));
        em.flush();

        //then
        List<CustomerCharacteristicEntity> all = customerCharacteristicRepository.findAll();
        assertEquals(1, all.size(), String.format("Actual list %s", all));
        CustomerCharacteristicEntity characteristicEntity = all.get(0);
        assertEntity(characteristicValue, CUSTOMER_ID, characteristicEntity);
    }

    @Test
    void shouldAddDefaultCharacteristics() {
        //when
        customerService.patchCustomer(CUSTOMER_ID, List.of(new PatchOperation()
            .op("add")
            .path("/characteristic/-")
            .value(new Characteristic()
                .name(LANGUAGE)
                .value(UA))
        ));
        em.flush();

        //then: the default value should not get into db
        List<CustomerCharacteristicEntity> all = customerCharacteristicRepository.findAll();
        assertEquals(0, all.size(), String.format("Actual list %s", all));
    }

    @Test
    void shouldUpdateCharacteristics() {
        //given
        shouldAddCharacteristics();

        Characteristic updatedCharacteristic = new Characteristic()
            .name(LANGUAGE)
            .value(EN);

        //when
        customerService.patchCustomer(CUSTOMER_ID, List.of(new PatchOperation()
            .op("replace")
            .path("/characteristic/-")
            .value(updatedCharacteristic)
        ));
        em.flush();

        //then
        List<CustomerCharacteristicEntity> all = customerCharacteristicRepository.findAll();
        assertEquals(1, all.size(), String.format("Actual list %s", all));
        CustomerCharacteristicEntity characteristicEntity = all.get(0);
        assertEntity(updatedCharacteristic, CUSTOMER_ID, characteristicEntity);
    }

    @Test
    public void shouldDeleteCharacteristic() {
        //given
        shouldAddCharacteristics();

        //when
        customerService.patchCustomer(CUSTOMER_ID, List.of(new PatchOperation()
            .op("remove")
            .path("/characteristic/-")
            .value(new Characteristic()
                .name(LANGUAGE).value(RU))
        ));
        em.flush();

        //then
        List<CustomerCharacteristicEntity> all = customerCharacteristicRepository.findAll();
        assertTrue(all.isEmpty(), String.format("Actual list %s", all));
    }

    @Test
    void shouldThrowExceptionIfCharacteristicIsNotConfigured() {
        //when
        try {
            customerService.patchCustomer(CUSTOMER_ID, List.of(new PatchOperation()
                .op("add")
                .path("/characteristic/-")
                .value(new Characteristic()
                    .name("foo")
                    .value("bar"))
            ));
            fail();
        } catch (BusinessException e) {
            assertEquals("error.characteristic.invalid", e.getCode());
            assertEquals("Invalid characteristic foo", e.getMessage());
        }

    }

    @Test
    void shouldGetCustomerBySpecification() {
        //given:
        customerCharacteristicRepository.save(CustomerCharacteristicEntity.builder()
            .customerId(CUSTOMER_ID)
            .key(LANGUAGE)
            .value(RU).build());
        em.flush();

        //when:
        List<Customer> result = customerService.getCustomerBySpecification(Specification.where(
            (Specification<CustomerCharacteristicEntity>) (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(CustomerCharacteristicEntity_.CUSTOMER_ID), CUSTOMER_ID)));

        //then:
        assertEquals(1, result.size(), String.format("Actual result: %s", result));
        assertEquals(characteristicValue, result.get(0).getCharacteristic().get(0));
    }

    @Test
    void shouldGetCharacteristics() {
        //given:
        shouldAddCharacteristics();

        //when:
        List<Customer> result = customerService.getCustomer(CUSTOMER_ID,
            "profile", LANGUAGE);
        em.flush();

        //then:
        assertFalse(result.isEmpty());
        Customer customerAct = result.get(0);
        assertEquals(List.of(characteristicValue), customerAct.getCharacteristic());
        assertEquals(1, result.size(), String.format("Actual result: %s", result));
    }

    @Test
    void shouldGetDefaultCharacteristics() {
        //given:

        //when:
        List<Customer> result = customerService.getCustomer(CUSTOMER_ID,
            "profile", LANGUAGE);
        em.flush();

        //then:
        assertFalse(result.isEmpty());
        Customer customerAct = result.get(0);
        assertEquals(List.of(new Characteristic()
                .name(LANGUAGE).value(UA)),
            customerAct.getCharacteristic());
        assertEquals(1, result.size(), String.format("Actual result: %s", result));
    }

    private void assertEntity(Characteristic characteristicValue, String customerId,
                              CustomerCharacteristicEntity characteristicEntity) {

        assertEquals(characteristicValue.getName(), characteristicEntity.getKey());
        assertEquals(characteristicValue.getValue(), characteristicEntity.getValue());
        assertEquals(customerId, characteristicEntity.getCustomerId());
    }

}

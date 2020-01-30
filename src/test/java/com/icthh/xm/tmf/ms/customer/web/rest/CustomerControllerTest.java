package com.icthh.xm.tmf.ms.customer.web.rest;

import com.icthh.xm.tmf.ms.customer.CustomerApp;
import com.icthh.xm.tmf.ms.customer.config.SecurityBeanOverrideConfiguration;
import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.repository.CustomerEntityRepository;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.icthh.xm.tmf.ms.customer.util.FileUtils.readAsString;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WithMockUser(authorities = "SUPER-ADMIN")
@SpringBootTest(classes = {CustomerApp.class, SecurityBeanOverrideConfiguration.class})
public class CustomerControllerTest {

    @Autowired
    private MockMvc httpMock;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Before
    public void beforeEach() {
        customerEntityRepository.saveAll(asList(
            CustomerCharacteristicEntity.builder()
                .customerId(2L)
                .key("language")
                .value("en")
                .build(),

            CustomerCharacteristicEntity.builder()
                .customerId(3L)
                .key("language")
                .value("en")
                .build()
        ));
    }

    @After
    public void afterEach() {
        customerEntityRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    public void shouldPatchCustomerNewCharacteristics() {
        httpMock.perform(patch("/api/customerManagement/v3/customer/1")
            .contentType(APPLICATION_JSON)
            .content(readAsString("customerUpdateWithNewCharacteristics.json")))
            .andExpect(content().json(readAsString("expectedCustomerWithNewCharacteristic.json"), false))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void shouldPatchCustomerWithDefaultCharacteristic() {
        httpMock.perform(patch("/api/customerManagement/v3/customer/2")
            .contentType(APPLICATION_JSON)
            .content(readAsString("customerUpdateWithDefaultCharacteristics.json")))
            .andExpect(content().json(readAsString("expectedUpdatedCustomerWithDefaultCharacteristic.json"), false))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void shouldGetCustomerExistingCharacteristics() {
        httpMock.perform(get("/api/customerManagement/v3/customer/3")
            .contentType(APPLICATION_JSON))
            .andExpect(content().json(readAsString("expectedCustomerWithExistingCharacteristic.json"), false))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void shouldGetCustomerDefaultCharacteristic() {
        httpMock.perform(get("/api/customerManagement/v3/customer/4")
            .contentType(APPLICATION_JSON))
            .andExpect(content().json(readAsString("expectedCustomerWithDefaultCharacteristic.json"), false))
            .andExpect(status().isOk());
    }

}

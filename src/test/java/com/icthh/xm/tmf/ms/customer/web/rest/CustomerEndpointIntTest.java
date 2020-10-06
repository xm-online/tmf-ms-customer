package com.icthh.xm.tmf.ms.customer.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.base.Charsets;
import com.icthh.xm.tmf.ms.customer.CustomerApp;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiController;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.config.SecurityBeanOverrideConfiguration;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CustomerApp.class})
public class CustomerEndpointIntTest {

    @Autowired
    private CustomerApiController customerApiController;
    @MockBean
    private CustomerApiDelegate customerApiDelegateMock;
    @Captor
    private ArgumentCaptor<List<PatchOperation>> patchOperationListCaptor;

    private MockMvc mockMvc;
    private String nameExpected = "test-name";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerApiController)
            .build();
    }

    @Test
    void shouldAcceptPatchRequestsOfGivenType() throws Exception {
        //given:
        String content = loadRequest("requests/customer/customer-patch-add.json");

        when(customerApiDelegateMock.patchCustomer(anyString(), any()))
            .thenReturn(ResponseEntity.ok(new Customer().name(nameExpected)));

        //when/then:
        mockMvc.perform(
            patch("/api/customerManagement/v3/customer/1")
                .contentType("application/json-patch+json")
                .content(content))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.name").value(nameExpected));

        //then:
        verify(customerApiDelegateMock).patchCustomer(eq("1"), patchOperationListCaptor.capture());
        List<PatchOperation> operationsActual = patchOperationListCaptor.getValue();
        assertEquals(List.of(
            new PatchOperation()
                .op("add")
                .path("/characteristic/-")
                .value(new Characteristic()
                    .name("REGISTRATION-TOKEN")
                    .value("a4dc2")),
            new PatchOperation()
                .op("replace")
                .path("/characteristic/-")
                .value(new Characteristic()
                    .name("LANGUAGE")
                    .value("RU"))
        ), operationsActual);
        verifyNoMoreInteractions(customerApiDelegateMock);
    }

    @Test
    void shouldAcceptGetRequests() throws Exception {
        //given:
        when(customerApiDelegateMock.retrieveCustomer("1", "p", "f"))
            .thenReturn(ResponseEntity.ok(List.of(new Customer().name(nameExpected))));

        //when/then:
        mockMvc.perform(
            get("/api/customerManagement/v3/customer/1?profile=p&fields=f"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(nameExpected));
    }

    private String loadRequest(String name) throws IOException {
        return IOUtils.toString(new ClassPathResource(name).getInputStream(), Charsets.UTF_8);
    }
}

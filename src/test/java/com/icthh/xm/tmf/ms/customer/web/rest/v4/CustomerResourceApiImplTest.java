package com.icthh.xm.tmf.ms.customer.web.rest.v4;

import static com.icthh.xm.tmf.ms.customer.web.rest.TestUtil.loadRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.icthh.xm.tmf.ms.customer.CustomerApp;
import com.icthh.xm.tmf.ms.customer.api.v4.CustomerResourceApiController;
import com.icthh.xm.tmf.ms.customer.config.SecurityBeanOverrideConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CustomerApp.class})
public class CustomerResourceApiImplTest {

    @Autowired
    private CustomerResourceApiController customerApiController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerApiController)
            .build();
    }

    @Test
    public void shouldReturnNotImplemented() throws Exception {
        String content = loadRequest("requests/customer/v4/customer-patch-request.json");

        mockMvc.perform(
            patch("/api/customerManagement/v4/customer/123")
                .contentType("application/json-patch+json")
                .content(content))
            .andExpect(status().isNotImplemented())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}

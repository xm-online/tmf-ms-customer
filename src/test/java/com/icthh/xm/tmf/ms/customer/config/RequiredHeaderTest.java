package com.icthh.xm.tmf.ms.customer.config;


import com.icthh.xm.commons.i18n.error.web.ExceptionTranslator;
import com.icthh.xm.tmf.ms.customer.CustomerApp;
import com.icthh.xm.tmf.ms.customer.validation.RequiredHeaderAspect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CustomerApp.class, RequiredHeaderAspect.class, ExceptionTranslator.class})
public class RequiredHeaderTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private WebConfigurerTestController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(exceptionTranslator)
            .build();
    }

    @Test
    public void testRequiredHeaderWhenEmpty() throws Exception {
        mockMvc.perform(
            get("/test/required-header")
                .header("Profile", "value")
                .header("Test", "value"))
            .andExpect(status().isOk());
    }

    @Test
    public void testRequiredHeaderWhenTwoHeaders() throws Exception {
        mockMvc.perform(
            get("/test/required-header"))
            .andExpect(status().isBadRequest());
    }

}

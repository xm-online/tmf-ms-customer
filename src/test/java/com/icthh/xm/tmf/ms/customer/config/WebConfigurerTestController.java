package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.tmf.ms.customer.validation.RequiredHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebConfigurerTestController {

    @GetMapping("/api/test-cors")
    public void testCorsOnApiPath() {
    }

    @GetMapping("/test/test-cors")
    public void testCorsOnOtherPath() {
    }

    @GetMapping("/test/required-header")
    @RequiredHeader(values = {"Profile", "Test"})
    public void testRequiredHeader() {
        System.out.println();
    }
}

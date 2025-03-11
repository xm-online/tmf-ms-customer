package com.icthh.xm.tmf.ms.customer;

import com.icthh.xm.tmf.ms.customer.config.IntegrationTestConfiguration;
import com.icthh.xm.tmf.ms.customer.config.TenantConfigMockConfiguration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = {
    CustomerApp.class,
    IntegrationTestConfiguration.class,
    TenantConfigMockConfiguration.class,
})
@Tag("com.icthh.xm.tmf.ms.customer.AbstractSpringBootTest")
@ExtendWith(SpringExtension.class)
public abstract class AbstractSpringBootTest {

    // TODO: To speedup test:
    //      - find all cases which break Spring context like @MockBean and fix.
    //      - separate tests by categories: Unit, SpringBoot, WebMwc

}

package com.icthh.xm.ms.mstemplate;

import com.icthh.xm.commons.lep.config.LepConfiguration;
import com.icthh.xm.ms.mstemplate.config.IntegrationTestConfiguration;
import com.icthh.xm.ms.mstemplate.config.TestLepConfiguration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = {
        MstemplateApp.class,
        LepConfiguration.class,
        IntegrationTestConfiguration.class,
        TestLepConfiguration.class,
})
@Tag("com.icthh.xm.ms.mstemplate.AbstractSpringBootTest")
@ExtendWith(SpringExtension.class)
public abstract class AbstractSpringBootTest {

    // TODO: To speedup test:
    //      - find all cases which break Spring context like @MockBean and fix.
    //      - separate tests by categories: Unit, SpringBoot, WebMwc

}

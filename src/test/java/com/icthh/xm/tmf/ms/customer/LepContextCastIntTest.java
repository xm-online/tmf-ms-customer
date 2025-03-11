package com.icthh.xm.tmf.ms.customer;

import static org.mockito.Mockito.when;

import com.icthh.xm.commons.config.client.service.TenantAliasService;
import com.icthh.xm.commons.config.domain.TenantAliasTree;
import com.icthh.xm.commons.lep.XmLepScriptConfigServerResourceLoader;
import com.icthh.xm.commons.lep.api.LepManagementService;
import com.icthh.xm.commons.tenant.TenantContextHolder;
import com.icthh.xm.commons.tenant.TenantContextUtils;
import com.icthh.xm.tmf.ms.customer.config.IntegrationTestConfiguration.TestLepService;
import com.icthh.xm.tmf.ms.customer.config.lep.LepContext;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Should be in EVERY XM MICROSERVICE, THAT USE LEP. When you add new field to lepContext this test help to not forget
 * add it to class LepContext.
 */
@Slf4j
@Tag("com.icthh.xm.tmf.ms.customer.AbstractSpringBootTest")
public class LepContextCastIntTest extends AbstractSpringBootTest {

    @Autowired
    private TenantContextHolder tenantContextHolder;

    @Autowired
    private LepManagementService lepManagementService;

    @Autowired
    private TenantAliasService tenantAliasService;

    @Autowired
    private XmLepScriptConfigServerResourceLoader leps;

    @Autowired
    private TestLepService testLepService;

    @Mock
    private TenantAliasTree tenantAliasTree;

    @SneakyThrows
    @BeforeEach
    public void setup() {
        TenantContextUtils.setTenant(tenantContextHolder, "TEST_TENANT");
        when(tenantAliasService.getTenantAliasTree()).thenReturn(tenantAliasTree);
    }

    @AfterEach
    public void tearDown() {
        lepManagementService.endThreadContext();
        tenantContextHolder.getPrivilegedContext().destroyCurrentContext();
    }

    @Test
    @SneakyThrows
    void testLepContextCast() {
        try (var context = lepManagementService.beginThreadContext()) {
            String prefix = "/config/tenants/TEST_TENANT/customer/lep/test/";
            String key = prefix + "ScriptWithAround$$around.groovy";
            String body = "import com.icthh.xm.tmf.ms.customer.config.lep.LepContext;\nLepContext context = lepContext\nreturn ['context':context]";
            leps.onRefresh(key, body);
            Map<String, Object> result = testLepService.sayHello();
            Assertions.assertInstanceOf(LepContext.class, result.get("context"));
            leps.onRefresh(key, null);
        }
    }

}

package com.icthh.xm.tmf.ms.customer.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsExecutor;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.lep.spring.SpringLepProcessingApplicationListener;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.lep.api.ScopedContext;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.service.SeparateTransactionExecutor;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.*;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@RequiredArgsConstructor
public class XmMsLepProcessingApplicationListener extends SpringLepProcessingApplicationListener {

    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;
    private final SeparateTransactionExecutor transactionExecutor;
    private final CustomerService customerService;
    private final XmAuthenticationContextHolder xmAuthenticationContextHolder;
    private final MeterRegistry meterRegistry;

    @Override
    protected void bindExecutionContext(ScopedContext executionContext) {
        // services
        Map<String, Object> services = new HashMap<>();
        services.put(BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE, tenantConfigService);
        services.put(BINDING_SUB_KEY_PERMISSION_SERVICE, permissionCheckService);
        services.put(BINDING_SUB_KEY_SERVICE_SEPARATE_TRANSACTION_EXECUTOR, transactionExecutor);
        services.put(BINDING_SUB_KEY_XM_AUTHENTICATION_CONTEXT_HOLDER, xmAuthenticationContextHolder);
        services.put(BINDING_SUB_KEY_CUSTOMER_SERVICE, customerService);

        executionContext.setValue(BINDING_KEY_COMMONS, new CommonsExecutor(commonsService));
        executionContext.setValue(BINDING_KEY_SERVICES, services);
        executionContext.setValue(BINDING_KEY_METER_REGISTRY, meterRegistry);

        // templates
        Map<String, Object> templates = new HashMap<>();
        templates.put(BINDING_SUB_KEY_TEMPLATE_REST, restTemplate);
        templates.put(BINDING_SUB_KEY_TEMPLATE_JDBC, jdbcTemplate);

        executionContext.setValue(BINDING_KEY_TEMPLATES, templates);
    }
}


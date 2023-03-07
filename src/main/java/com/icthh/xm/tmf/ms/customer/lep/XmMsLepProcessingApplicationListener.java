package com.icthh.xm.tmf.ms.customer.lep;

import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_COMMONS;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_SERVICES;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_TEMPLATES;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_CUSTOMER_SERVICE;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_PERMISSION_SERVICE;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_SERVICE_SEPARATE_TRANSACTION_EXECUTOR;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_TEMPLATE_JDBC;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_TEMPLATE_REDIS;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_TEMPLATE_REST;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_XM_AUTHENTICATION_CONTEXT_HOLDER;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsExecutor;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.lep.spring.SpringLepProcessingApplicationListener;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.lep.api.ScopedContext;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.service.SeparateTransactionExecutor;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@RequiredArgsConstructor
public class XmMsLepProcessingApplicationListener extends SpringLepProcessingApplicationListener {

    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;
    private final SeparateTransactionExecutor transactionExecutor;
    private final CustomerService customerService;
    private final XmAuthenticationContextHolder xmAuthenticationContextHolder;

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

        // templates
        Map<String, Object> templates = new HashMap<>();
        templates.put(BINDING_SUB_KEY_TEMPLATE_REST, restTemplate);
        templates.put(BINDING_SUB_KEY_TEMPLATE_REDIS, redisTemplate);
        templates.put(BINDING_SUB_KEY_TEMPLATE_JDBC, jdbcTemplate);

        executionContext.setValue(BINDING_KEY_TEMPLATES, templates);
    }
}


package com.icthh.xm.tmf.ms.customer.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsExecutor;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.lep.spring.SpringLepProcessingApplicationListener;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.lep.api.ScopedContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_COMMONS;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_SERVICES;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_KEY_TEMPLATES;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_PERMISSION_SERVICE;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE;
import static com.icthh.xm.tmf.ms.customer.lep.LepXmCustomerConstants.BINDING_SUB_KEY_TEMPLATE_REST;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@RequiredArgsConstructor
public class XmMsLepProcessingApplicationListener extends SpringLepProcessingApplicationListener {


    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;


    @Override
    protected void bindExecutionContext(ScopedContext executionContext) {
        // services
        Map<String, Object> services = new HashMap<>();
        services.put(BINDING_SUB_KEY_SERVICE_TENANT_CONFIG_SERICE, tenantConfigService);
        services.put(BINDING_SUB_KEY_PERMISSION_SERVICE, permissionCheckService);

        executionContext.setValue(BINDING_KEY_COMMONS, new CommonsExecutor(commonsService));
        executionContext.setValue(BINDING_KEY_SERVICES, services);

        // templates
        Map<String, Object> templates = new HashMap<>();
        templates.put(BINDING_SUB_KEY_TEMPLATE_REST, restTemplate);

        executionContext.setValue(BINDING_KEY_TEMPLATES, templates);
    }

}


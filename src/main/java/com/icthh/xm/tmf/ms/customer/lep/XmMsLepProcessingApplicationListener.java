package com.icthh.xm.tmf.ms.customer.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.api.BaseLepContext;
import com.icthh.xm.commons.lep.api.LepContextFactory;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.tmf.ms.customer.config.lep.LepContext;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.service.SeparateTransactionExecutor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The {@link XmMsLepProcessingApplicationListener} class.
 */
@Component
public class XmMsLepProcessingApplicationListener implements LepContextFactory {

    private final TenantConfigService tenantConfigService;

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final CommonsService commonsService;
    private final PermissionCheckService permissionCheckService;
    private final SeparateTransactionExecutor transactionExecutor;
    private final CustomerService customerService;
    private final XmAuthenticationContextHolder xmAuthenticationContextHolder;
    private final MeterRegistry meterRegistry;

    public XmMsLepProcessingApplicationListener(TenantConfigService tenantConfigService,
        @Qualifier("customerRestTemplate") RestTemplate restTemplate,
        JdbcTemplate jdbcTemplate,
        CommonsService commonsService,
        PermissionCheckService permissionCheckService,
        SeparateTransactionExecutor transactionExecutor,
        CustomerService customerService,
        XmAuthenticationContextHolder xmAuthenticationContextHolder,
        MeterRegistry meterRegistry) {
        this.tenantConfigService = tenantConfigService;
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.commonsService = commonsService;
        this.permissionCheckService = permissionCheckService;
        this.transactionExecutor = transactionExecutor;
        this.customerService = customerService;
        this.xmAuthenticationContextHolder = xmAuthenticationContextHolder;
        this.meterRegistry = meterRegistry;
    }

    @Override
    public BaseLepContext buildLepContext(LepMethod lepMethod) {
        LepContext lepContext = new LepContext();
        lepContext.services = new LepContext.LepServices();
        // services
        lepContext.services.tenantConfigService = tenantConfigService;
        lepContext.services.permissionService = permissionCheckService;
        lepContext.services.separateTransactionExecutor = transactionExecutor;
        lepContext.services.xmAuthenticationContextHolder = xmAuthenticationContextHolder;
        lepContext.services.customerService = customerService;

        lepContext.meterRegistry = meterRegistry;

        // templates
        lepContext.templates = new LepContext.LepTemplates();
        lepContext.templates.rest = restTemplate;
        lepContext.templates.jdbc = jdbcTemplate;

        return lepContext;
    }
}


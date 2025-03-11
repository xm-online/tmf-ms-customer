package com.icthh.xm.tmf.ms.customer.config.lep;


import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.api.BaseLepContext;
import com.icthh.xm.commons.logging.trace.TraceService.TraceServiceField;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.service.SeparateTransactionExecutor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

public class LepContext extends BaseLepContext implements TraceServiceField {

    public LepServices services;
    public LepTemplates templates;
    public MeterRegistry meterRegistry;

    public static class LepServices {

        public TenantConfigService tenantConfigService;
        public PermissionCheckService permissionService;
        public SeparateTransactionExecutor separateTransactionExecutor;
        public CustomerService customerService;
        public XmAuthenticationContextHolder xmAuthenticationContextHolder;
    }

    public static class LepTemplates {

        public RestTemplate rest;
        public JdbcTemplate jdbc;
    }

}


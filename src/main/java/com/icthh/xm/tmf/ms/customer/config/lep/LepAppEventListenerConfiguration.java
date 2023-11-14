package com.icthh.xm.tmf.ms.customer.config.lep;

import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.lep.commons.CommonsService;
import com.icthh.xm.commons.permission.service.PermissionCheckService;
import com.icthh.xm.commons.security.XmAuthenticationContextHolder;
import com.icthh.xm.tmf.ms.customer.lep.XmMsLepProcessingApplicationListener;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.service.SeparateTransactionExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * The {@link LepAppEventListenerConfiguration} class.
 */
@Configuration
public class LepAppEventListenerConfiguration {

    @Bean
    XmMsLepProcessingApplicationListener buildLepProcessingApplicationListener(
        TenantConfigService tenantConfigService,
        @Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate,
        CommonsService commonsService,
        PermissionCheckService permissionCheckService,
        JdbcTemplate jdbcTemplate,
        SeparateTransactionExecutor transactionExecutor,
        CustomerService customerService,
        XmAuthenticationContextHolder xmAuthenticationContextHolder) {

        return new XmMsLepProcessingApplicationListener(
            tenantConfigService,
            restTemplate,
            jdbcTemplate,
            commonsService,
            permissionCheckService,
            transactionExecutor,
            customerService,
            xmAuthenticationContextHolder
        );
    }

}

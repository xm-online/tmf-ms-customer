package com.icthh.xm.tmf.ms.customer.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.icthh.xm.commons.config.client.repository.TenantConfigRepository;
import com.icthh.xm.commons.config.client.repository.TenantListRepository;
import com.icthh.xm.commons.config.client.service.TenantAliasService;
import com.icthh.xm.commons.config.client.service.TenantConfigService;
import com.icthh.xm.commons.web.spring.TenantVerifyInterceptor;
import java.util.Collections;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantConfigMockConfiguration {

    private Set<String> tenants = Collections.singleton("XM");

    @Bean
    public TenantListRepository tenantListRepository() {
        TenantListRepository mockTenantListRepository = mock(TenantListRepository.class);

        doAnswer(mvc -> tenants.add(mvc.getArguments()[0].toString()))
            .when(mockTenantListRepository).addTenant(any());

        doAnswer(mvc -> tenants.remove(mvc.getArguments()[0].toString()))
            .when(mockTenantListRepository).deleteTenant(any());

        when(mockTenantListRepository.getTenants()).thenReturn(tenants);
        return mockTenantListRepository;
    }

    @Bean
    public TenantConfigRepository tenantConfigRepository() {
        return mock(TenantConfigRepository.class);
    }

    @Bean
    public TenantConfigService tenantConfigService() {
        return mock(TenantConfigService.class);
    }

    @Bean
    public TenantVerifyInterceptor tenantVerifyInterceptor() {
        return mock(TenantVerifyInterceptor.class);
    }

    @Bean
    public TenantAliasService tenantAliasService() {
        return mock(TenantAliasService.class);
    }
}

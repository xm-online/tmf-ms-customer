package com.icthh.xm.tmf.ms.customer.config;

import static com.icthh.xm.commons.config.client.config.XmRestTemplateConfiguration.XM_CONFIG_REST_TEMPLATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.icthh.xm.commons.config.client.repository.TenantConfigRepository;
import com.icthh.xm.commons.config.client.repository.TenantListRepository;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.lep.spring.LepUpdateMode;
import com.icthh.xm.commons.logging.config.LoggingConfigService;
import com.icthh.xm.commons.logging.config.LoggingConfigServiceStub;
import com.icthh.xm.commons.security.jwt.TokenProvider;
import com.icthh.xm.commons.security.oauth2.JwtVerificationKeyClient;
import com.icthh.xm.commons.web.spring.TenantVerifyInterceptor;
import com.icthh.xm.tmf.ms.customer.config.lep.LepConfiguration;
import io.prometheus.client.CollectorRegistry;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(value = LepConfiguration.class)
public class IntegrationTestConfiguration {

    private Set<String> tenants = new HashSet<>();

    {
        tenants.add("XM");
        tenants.add("DEMO");
        tenants.add("TEST");
        tenants.add("RESINTTEST");
    }

    @Bean
    public CollectorRegistry collectorRegistry() {
        return new CollectorRegistry();
    }

    @Bean
    public TenantListRepository tenantListRepository() {
        TenantListRepository mockTenantListRepository = mock(TenantListRepository.class);
        doAnswer(mvc -> tenants.add(mvc.getArguments()[0].toString())).when(mockTenantListRepository).addTenant(any());
        doAnswer(mvc -> tenants.remove(mvc.getArguments()[0].toString())).when(mockTenantListRepository)
            .deleteTenant(any());
        when(mockTenantListRepository.getTenants()).thenReturn(tenants);
        return mockTenantListRepository;
    }

    @Bean
    public TenantConfigRepository tenantConfigRepository() {
        return mock(TenantConfigRepository.class);
    }

    @Bean
    public TenantVerifyInterceptor tenantVerifyInterceptor() {
        return mock(TenantVerifyInterceptor.class);
    }

    @Bean
    @Primary
    // TODO remove implementation
    public JwtVerificationKeyClient jwtVerificationKeyClient() {
        return () -> readFile("config/public.cer");
    }

    @Bean
    @Primary
    public TokenProvider tokenProvider() {
        return mock(TokenProvider.class);
    }

    @Bean
    @ConditionalOnMissingBean(value = RestTemplateCustomizer.class)
    public RestTemplateCustomizer restTemplateCustomizer() {
        return mock(RestTemplateCustomizer.class);
    }

    @Bean(XM_CONFIG_REST_TEMPLATE)
    public RestTemplate restTemplate(RestTemplateCustomizer customizer) {
        return mock(RestTemplate.class);
    }

    @SneakyThrows
    public static byte[] readFile(String path) {
        return IntegrationTestConfiguration.class.getClassLoader().getResourceAsStream(path).readAllBytes();
    }

    @Bean
    public LepUpdateMode lepUpdateMode() {
        return LepUpdateMode.SYNCHRONOUS;
    }

    @Bean
    public LoggingConfigService LoggingConfigService() {
        return new LoggingConfigServiceStub();
    }

    @Bean
    public TestLepService testLepService() {
        return new TestLepService();
    }

    @LepService(group = "test")
    public static class TestLepService {

        @LogicExtensionPoint("ScriptWithAround")
        public Map<String, Object> sayHello() {
            return Map.of();
        }
    }
}

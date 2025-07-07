package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.commons.config.client.config.XmTimeoutProperties;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfiguration {

    @ConditionalOnProperty(
        value = {"spring.cloud.loadbalancer.enabled"},
        havingValue = "true",
        matchIfMissing = true
    )
    @Bean("customerRestTemplate")
    public RestTemplate restTemplateWithLoadBalancer(RestTemplateBuilder restTemplateBuilder,
        RestTemplateCustomizer customizer,
        ObjectProvider<XmTimeoutProperties> timeoutPropertiesProvider) {
        log.info("restTemplateWithLoadBalancer creation with loadBalancer started");

        RestTemplate restTemplate = createRestTemplate(restTemplateBuilder, timeoutPropertiesProvider);

        customizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean("customerRestTemplate")
    @ConditionalOnMissingBean(name = "customerRestTemplate")
    public RestTemplate vanillaRestTemplate(RestTemplateBuilder restTemplateBuilder,
        ObjectProvider<XmTimeoutProperties> timeoutPropertiesProvider) {
        log.info("vanillaRestTemplate creation without loadBalancer started");
        return createRestTemplate(restTemplateBuilder, timeoutPropertiesProvider);
    }

    private RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder,
        ObjectProvider<XmTimeoutProperties> timeoutPropertiesProvider) {

        XmTimeoutProperties timeoutProperties = timeoutPropertiesProvider.getIfAvailable();

        if (Objects.nonNull(timeoutProperties)) {
            log.info("customerCreateRestTemplate with timeouts={}", timeoutProperties);
            return restTemplateBuilder
                .setConnectTimeout(timeoutProperties.getConnectionTimeout())
                .setReadTimeout(timeoutProperties.getReadTimeout())
                .build();
        }
        log.info("customerCreateRestTemplate without timeouts");
        return restTemplateBuilder.build();
    }
}

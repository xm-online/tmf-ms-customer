package com.icthh.xm.tmf.ms.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    public RestTemplate restTemplateWithLoadBalancer(RestTemplateCustomizer customizer) {
        log.info("restTemplateWithLoadBalancer creation with loadBalancer started");
        RestTemplate restTemplate = new RestTemplate();
        customizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean("customerRestTemplate")
    @ConditionalOnMissingBean(name = "customerRestTemplate")
    public RestTemplate vanillaRestTemplate() {
        log.info("vanillaRestTemplate creation without loadBalancer started");
        return new RestTemplate();
    }
}

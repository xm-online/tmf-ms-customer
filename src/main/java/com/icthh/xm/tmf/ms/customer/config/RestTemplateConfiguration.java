package com.icthh.xm.tmf.ms.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfiguration {

    @Bean
    @ConditionalOnProperty(
        value = {"spring.cloud.loadbalancer.enabled"},
        havingValue = "true"
    )
    public RestTemplate customerRestTemplate(RestTemplateCustomizer customizer) {
        log.info("customerRestTemplate creation with loadBalancer started");
        RestTemplate restTemplate = new RestTemplate();
        customizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate customerRestTemplate() {
        log.info("customerRestTemplate creation without loadBalancer started");
        return new RestTemplate();
    }
}

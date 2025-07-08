package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.tmf.ms.customer.config.ApplicationProperties.CustomerTimeoutProperties;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
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
        ApplicationProperties applicationProperties) {
        log.info("restTemplateWithLoadBalancer creation with loadBalancer started");

        RestTemplate restTemplate = createRestTemplate(restTemplateBuilder,
            applicationProperties.getCustomerTimeouts());

        customizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean("customerRestTemplate")
    @ConditionalOnMissingBean(name = "customerRestTemplate")
    public RestTemplate vanillaRestTemplate(RestTemplateBuilder restTemplateBuilder,
        ApplicationProperties applicationProperties) {
        log.info("vanillaRestTemplate creation without loadBalancer started");
        return createRestTemplate(restTemplateBuilder, applicationProperties.getCustomerTimeouts());
    }

    private RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder,
        CustomerTimeoutProperties customerTimeoutProperties) {

        if (Objects.nonNull(customerTimeoutProperties)) {
            log.info("customerCreateRestTemplate with timeouts={}", customerTimeoutProperties);
            return restTemplateBuilder
                .setConnectTimeout(customerTimeoutProperties.getConnectionTimeout())
                .setReadTimeout(customerTimeoutProperties.getReadTimeout())
                .build();
        }
        log.info("customerCreateRestTemplate without timeouts");
        return restTemplateBuilder.build();
    }
}

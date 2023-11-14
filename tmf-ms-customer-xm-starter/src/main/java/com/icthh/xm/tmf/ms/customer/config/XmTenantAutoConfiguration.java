package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.tmf.ms.customer.CustomerLanguageApiDelegate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConditionalOnClass(CustomerLanguageApiDelegate.class)
public class XmTenantAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CustomerLanguageApiDelegate customerLanguageApiDelegate(JdbcTemplate jdbcTemplate) {
        return new CustomerLanguageApiDelegate(jdbcTemplate);
    }
}

package com.icthh.xm.tmf.ms.customer.config;

import static com.icthh.xm.commons.config.domain.Configuration.of;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.icthh.xm.commons.config.client.repository.TenantConfigRepository;
import com.icthh.xm.commons.migration.db.tenant.provisioner.TenantDatabaseProvisioner;
import com.icthh.xm.commons.tenantendpoint.TenantManager;
import com.icthh.xm.commons.tenantendpoint.provisioner.TenantConfigProvisioner;
import com.icthh.xm.commons.tenantendpoint.provisioner.TenantListProvisioner;
import java.util.HashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
public class TenantManagerConfiguration {

    private static final String DEFAULT_CONFIG_PATH = "config/specs/default-customer.yml";

    @Bean
    public TenantManager tenantManager(
        TenantDatabaseProvisioner databaseProvisioner,
        TenantConfigProvisioner configProvisioner,
        TenantListProvisioner tenantListProvisioner
    ) {

        TenantManager manager = TenantManager.builder()
            .service(tenantListProvisioner)
            .service(databaseProvisioner)
            .service(configProvisioner)
            .build();
        log.info("Configured tenant manager: {}", manager);
        return manager;
    }

    @Bean
    @SneakyThrows
    public TenantConfigProvisioner tenantConfigProvisioner(
        TenantConfigRepository tenantConfigRepository,
        ApplicationProperties applicationProperties
    ) {

        TenantConfigProvisioner provisioner = TenantConfigProvisioner
            .builder()
            .tenantConfigRepository(tenantConfigRepository)
            .configuration(of().path(applicationProperties.getTenantCustomerPropertiesPathPattern())
                .content(readResource(DEFAULT_CONFIG_PATH))
                .build())
            .build();

        log.info("Configured tenant config provisioner: {}", provisioner);
        return provisioner;
    }

    @SneakyThrows
    private String readResource(String location) {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), UTF_8);
    }

}

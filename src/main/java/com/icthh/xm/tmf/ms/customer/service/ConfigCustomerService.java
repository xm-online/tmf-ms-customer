package com.icthh.xm.tmf.ms.customer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.icthh.xm.commons.config.client.api.RefreshableConfiguration;
import com.icthh.xm.commons.config.client.repository.TenantConfigRepository;
import com.icthh.xm.commons.tenant.TenantContextHolder;
import com.icthh.xm.commons.tenant.TenantContextUtils;
import com.icthh.xm.tmf.ms.customer.config.ApplicationProperties;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang.StringUtils.isBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigCustomerService implements RefreshableConfiguration {

    private static final String TENANT_NAME = "tenantName";

    private ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private final ConcurrentHashMap<String, CustomerCharacteristics> customerProperties = new ConcurrentHashMap<>();

    private final AntPathMatcher matcher = new AntPathMatcher();
    private final ApplicationProperties applicationProperties;
    private final TenantConfigRepository tenantConfigRepository;
    private final TenantContextHolder tenantContextHolder;


    public CustomerCharacteristics getConfig() {
        log.info("Her is what we have inside propeties {}", customerProperties);
        String tenantKey = TenantContextUtils.getRequiredTenantKeyValue(tenantContextHolder);
        String cfgTenantKey = tenantKey.toUpperCase();
        if (!customerProperties.containsKey(cfgTenantKey)) {
            throw new IllegalArgumentException("Tenant user customer configuration not found");
        }
        return customerProperties.get(cfgTenantKey);
    }

    @SneakyThrows
    public void updateConfig(String customerYml) {
        String tenantKey = TenantContextUtils.getRequiredTenantKeyValue(tenantContextHolder);
        String configName = applicationProperties.getTenantCustomerPropertiesName();

        mapper.readValue(customerYml, CustomerCharacteristics.class);

        tenantConfigRepository.updateConfig(tenantKey.toUpperCase(), "/" + configName, customerYml);
    }

    @Override
    public void onRefresh(String key, String config) {
        String pathPattern = applicationProperties.getTenantCustomerPropertiesPathPattern();
        try {
            String tenant = matcher.extractUriTemplateVariables(pathPattern, key).get(TENANT_NAME);
            if (isBlank(config)) {
                customerProperties.remove(tenant);
                log.info("Specification for tenant {} was removed", tenant);
            } else {
                CustomerCharacteristics spec = mapper.readValue(config, CustomerCharacteristics.class);
                customerProperties.put(tenant, spec);
                log.info("Specification for tenant {} was updated", tenant);
            }
        } catch (Exception e) {
            log.error("Error read specification from path " + key, e);
        }
    }

    @Override
    public boolean isListeningConfiguration(String key) {
        return matcher.match(applicationProperties.getTenantCustomerPropertiesPathPattern(), key);
    }

    @Override
    public void onInit(String key, String config) {
        onRefresh(key, config);
    }
}

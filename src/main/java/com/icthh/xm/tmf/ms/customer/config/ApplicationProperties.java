package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.commons.lep.TenantScriptStorage;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Customer.
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Getter
@Setter
public class ApplicationProperties {

    private boolean schedulerEnabled;
    private String kafkaSystemTopic;
    private String kafkaSystemQueue;
    private String exceptionTranslator;
    private boolean timelinesEnabled;
    private String dbSchemaSuffix;
    private final Retry retry = new Retry();
    private final Lep lep = new Lep();

    private List<String> tenantIgnoredPathList = Collections.emptyList();

    /**
     * Ant Path pattern to lookup customer property file (config ms)
     */
    private String tenantCustomerPropertiesPathPattern;

    @Getter
    @Setter
    public static class Lep {
        private TenantScriptStorage tenantScriptStorage;
        private String lepResourcePathPattern;
    }

    @Getter
    @Setter
    private static class Retry {
        private int maxAttempts;
        private long delay;
        private int multiplier;
    }

}

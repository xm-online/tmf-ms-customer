package com.icthh.xm.tmf.ms.customer.config;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String specificationPathPattern;
    private List<String> tenantIgnoredPathList = Collections.emptyList();
    private List<String> timelineIgnoredHttpMethods = Collections.emptyList();
    private CustomerTimeoutProperties customerTimeouts;

    /**
     * Ant Path pattern to lookup customer property file (config ms)
     */
    private String tenantCustomerPropertiesPathPattern;

    private boolean timelinesEnabled;
    private boolean kafkaEnabled;
    private boolean schedulerEnabled;

    private List<String> tenantWithCreationAccessList;
    private String kafkaSystemTopic;
    private String kafkaSystemQueue;
    private Integer kafkaMetadataMaxAge;
    private KafkaHealth kafkaHealth;

    @Data
    public static class KafkaHealth {

        private Boolean enabled;
    }

    @Data
    public static class CustomerTimeoutProperties {

        private Duration readTimeout;
        private Duration connectionTimeout;
    }
}

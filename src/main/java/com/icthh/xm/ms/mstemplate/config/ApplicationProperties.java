package com.icthh.xm.ms.mstemplate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String specificationPathPattern;
    private List<String> tenantIgnoredPathList = Collections.emptyList();
    private List<String> timelineIgnoredHttpMethods = Collections.emptyList();

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
}

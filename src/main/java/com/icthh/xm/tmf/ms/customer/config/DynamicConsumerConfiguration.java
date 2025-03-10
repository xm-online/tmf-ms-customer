package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.commons.config.client.repository.TenantListRepository;
import com.icthh.xm.commons.topic.service.DynamicConsumerConfigurationService;
import com.icthh.xm.commons.topic.service.TopicManagerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn("topicConfigurationService")
@Component
@Slf4j
public class DynamicConsumerConfiguration extends DynamicConsumerConfigurationService {


    public DynamicConsumerConfiguration(
        List<com.icthh.xm.commons.topic.service.DynamicConsumerConfiguration> dynamicConsumerConfigurations,
        TopicManagerService topicManagerService,
        TenantListRepository tenantListRepository) {
        super(dynamicConsumerConfigurations, topicManagerService, tenantListRepository);
    }
}

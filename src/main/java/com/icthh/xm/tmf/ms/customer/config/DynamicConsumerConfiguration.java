package com.icthh.xm.tmf.ms.customer.config;

import com.icthh.xm.commons.config.client.repository.TenantListRepository;
import com.icthh.xm.commons.topic.service.DynamicConsumerConfigurationService;
import com.icthh.xm.commons.topic.service.TopicManagerService;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DynamicConsumerConfiguration extends DynamicConsumerConfigurationService{


    public DynamicConsumerConfiguration(
        @Lazy List<com.icthh.xm.commons.topic.service.DynamicConsumerConfiguration> dynamicConsumerConfigurations,
        TopicManagerService topicManagerService,
        TenantListRepository tenantListRepository) {
        super(dynamicConsumerConfigurations, topicManagerService, tenantListRepository);
    }
}

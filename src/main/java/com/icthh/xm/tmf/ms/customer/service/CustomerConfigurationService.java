package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;

/**
 * Describes a service that deals with Customer ms configuration.
 */
public interface CustomerConfigurationService {

    /**
     * @return Customer characteristics configuration for tenant from the context
     */
    CustomerCharacteristics getConfig();
}

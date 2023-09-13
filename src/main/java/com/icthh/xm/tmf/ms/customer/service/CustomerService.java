package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Describes a service that deals with
 */
public interface CustomerService {

    /**
     * Gets customer by {@code id}.r5ew32
     *
     * @param id      unique identifier of the customer
     * @param profile customer profile
     * @param fields  comma-separated properties to provide in response
     * @return customer by id
     */
    List<Customer> getCustomer(String id, String profile, String fields);

    /**
     * Retrieves customers according to Spring data JPA {@code spec}.
     *
     * @param spec data spec
     * @return a list of customers matching the {@code spec}
     */
    List<Customer> getCustomerBySpecification(Specification<CustomerCharacteristicEntity> spec);

    /**
     * Performs JSON PATCH operation according to application/json-patch+json specification.
     *
     * @param id              unique identifier of the customer
     * @param patchOperations a list of the patch operations
     * @return patched values
     */
    Customer patchCustomer(String id, List<PatchOperation> patchOperations);
}

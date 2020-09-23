package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.domain.CustomerCharacteristicEntity;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface CustomerService {
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
     * @param id unique identifier of the customer
     * @param patchOperations
     * @return patched values
     */
    Customer patchCustomer(String id, List<PatchOperation> patchOperations);
}

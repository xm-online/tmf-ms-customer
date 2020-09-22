package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import java.util.List;

public interface CustomerService {
    List<Customer> getCustomer(String id, String profile, String fields);

    List<Customer> getCustomerFirebaseIds(List<String> ids);

    /**
     * Performs JSON PATCH operation according to application/json-patch+json specification.
     *
     * @param id unique identifier of the customer
     * @param patchOperations
     * @return patched values
     */
    Customer patchCustomer(String id, List<PatchOperation> patchOperations);
}

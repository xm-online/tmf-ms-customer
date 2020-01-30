package com.icthh.xm.tmf.ms.customer.service;

import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.CustomerUpdate;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomer(String id, String profile, String fields);

    Customer patchCustomer(String id, CustomerUpdate customer);
}

package com.icthh.xm.tmf.ms.customer.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.CustomerUpdate;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@Component
@RequiredArgsConstructor
@LepService(group = "service", name = "default")
public class CustomerDelegate implements CustomerApiDelegate {

    private final CustomerService customerService;

    @Timed
    @Override
    @LogicExtensionPoint(value = "RetrieveCustomer", resolver = ProfileKeyResolver.class)
//    @PreAuthorize("hasPermission({'id': #id}, 'CUSTOMER.GET')")
//    @PrivilegeDescription("Privilege to get a customer")
    public ResponseEntity<List<Customer>> retrieveCustomer(String id,
                                                           String profile,
                                                           String fields) {
        log.info("Try to get customer with database implemntation Hello ");
        return ok(customerService.getCustomer(id, profile, fields));
    }


    @Timed
    @Override
    @LogicExtensionPoint(value = "PatchCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id}, 'CUSTOMER.PATCH')")
    @PrivilegeDescription("Privilege to patch a customer")
    public ResponseEntity<Customer> patchCustomer(String id, CustomerUpdate customer) {
        return ok(customerService.patchCustomer(id, customer));
    }
}

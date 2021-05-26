package com.icthh.xm.tmf.ms.customer.web.rest;

import static org.springframework.http.ResponseEntity.ok;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.PatchCustomerProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@LepService(group = "service", name = "default")
@AllArgsConstructor
public class CustomerDelegate implements CustomerApiDelegate {

    private final CustomerService customerService;

    @Timed
    @LogicExtensionPoint(value = "RetrieveCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'profile': #profile}, 'CUSTOMER.GET')")
    @PrivilegeDescription("Privilege to get a customer")
    @Override
    public ResponseEntity<List<Customer>> retrieveCustomer(String id,
                                                           String profile,
                                                           String fields) {
        return ok(customerService.getCustomer(id, profile, fields));
    }

    @Timed
    @LogicExtensionPoint(value = "PatchCustomer", resolver = PatchCustomerProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'patchOperations': #operations}, 'CUSTOMER.PATCH')")
    @PrivilegeDescription("Privilege to patch a customer")
    @Override
    public ResponseEntity<Customer> patchCustomer(String id, List<PatchOperation> operations) {
        return ok(customerService.patchCustomer(id, operations));
    }
}

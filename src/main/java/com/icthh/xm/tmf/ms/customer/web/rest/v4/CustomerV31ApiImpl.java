package com.icthh.xm.tmf.ms.customer.web.rest.v4;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.customer.api.v31.CustomerV31ApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.PatchCustomerProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.v31.Customer;
import com.icthh.xm.tmf.ms.customer.model.v31.PatchOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@LepService(group = "service")
@AllArgsConstructor
public class CustomerV31ApiImpl implements CustomerV31ApiDelegate {

    @Timed
    @LogicExtensionPoint(value = "PatchCustomer", resolver = PatchCustomerProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'patchOperations': #operations}, 'CUSTOMER.PATCH')")
    @PrivilegeDescription("Privilege to patch a customer")
    @Override
    public ResponseEntity<Customer> patchCustomer(String id, List<PatchOperation> operations) {
        return CustomerV31ApiDelegate.super.patchCustomer(id, operations);
    }
}

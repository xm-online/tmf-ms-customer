package com.icthh.xm.tmf.ms.customer.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.customer.api.v4.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.v4.CustomerUpdateV4;
import com.icthh.xm.tmf.ms.customer.model.v4.CustomerV4;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@LepService(group = "service", name = "default")
@AllArgsConstructor
public class CustomerDelegateV4 implements CustomerApiDelegate {

    @Timed
    @LogicExtensionPoint(value = "RetrieveCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'profile': #profile}, 'CUSTOMER.GET')")
    @PrivilegeDescription("Privilege to get a customer")
    @Override
    public ResponseEntity<CustomerV4> retrieveCustomer(String id,
                                                       String fields) {
        return ResponseEntity.ok().build();
    }


    @Timed
    @LogicExtensionPoint(value = "PatchCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'patchOperations': #operations}, 'CUSTOMER.PATCH')")
    @PrivilegeDescription("Privilege to patch a customer")
    @Override
    public ResponseEntity<CustomerV4> patchCustomer(String id, CustomerUpdateV4 customer) {
        return ResponseEntity.ok().build();
    }
}

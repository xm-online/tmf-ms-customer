package com.icthh.xm.tmf.ms.customer.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@LepService(group = "service", name = "default")
public class CustomerDelegate implements CustomerApiDelegate {

    @Timed
    @LogicExtensionPoint(value = "RetrieveCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id}, 'CUSTOMER.GET')")
    @Override
    public ResponseEntity<List<Customer>> retrieveCustomer(String id,
                                                           String profile,
                                                           String fields) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}

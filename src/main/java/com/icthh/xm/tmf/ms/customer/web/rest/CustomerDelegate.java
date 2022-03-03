package com.icthh.xm.tmf.ms.customer.web.rest;

import static org.springframework.http.ResponseEntity.ok;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileHeaderKeyResolver;
import com.icthh.xm.tmf.ms.customer.lep.keyresolver.ProfileKeyResolver;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.CustomerCreate;
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
    @PreAuthorize("hasPermission({'id': #id, 'profile': #profile, 'requestHeaderExtractor': @requestHeaderExtractor}, 'CUSTOMER.GET')")
    @PrivilegeDescription("Privilege to get a customer")
    @Override
    public ResponseEntity<List<Customer>> retrieveCustomer(String id,
                                                           String profile,
                                                           String fields) {
        return ok(customerService.getCustomer(id, profile, fields));
    }

    @Timed
    @LogicExtensionPoint(value = "PatchCustomer", resolver = ProfileHeaderKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'patchOperations': #operations, 'requestHeaderExtractor': @requestHeaderExtractor}, 'CUSTOMER.PATCH')")
    @PrivilegeDescription("Privilege to patch a customer")
    @Override
    public ResponseEntity<Customer> patchCustomer(String id, List<PatchOperation> operations) {
        return ok(customerService.patchCustomer(id, operations));
    }

    @Timed
    @LogicExtensionPoint(value = "RetrieveCustomer", resolver = ProfileKeyResolver.class)
    @PreAuthorize("hasPermission({'id': #id, 'profile': #profile, 'requestHeaderExtractor': @requestHeaderExtractor}, 'CUSTOMER.GET')")
    @PrivilegeDescription("Privilege to get a customers list")
    @Override
    public ResponseEntity<List<Customer>> listCustomer(String fields, Integer offset, Integer limit) {
        return ok().build();
    }

    @Timed
    @LogicExtensionPoint(value = "CreateCustomer", resolver = ProfileHeaderKeyResolver.class)
    @PreAuthorize("hasPermission({'customer': #customer, 'requestHeaderExtractor': @requestHeaderExtractor}, 'CUSTOMER.CREATE')")
    @PrivilegeDescription("Privilege to create customer")
    @Override
    public ResponseEntity<Customer> createCustomer(CustomerCreate customer) {
        return ok().build();
    }

    @Timed
    @LogicExtensionPoint(value = "DeleteCustomer", resolver = ProfileHeaderKeyResolver.class)
    @PreAuthorize("hasPermission({'customer': #customer, 'requestHeaderExtractor': @requestHeaderExtractor}, 'CUSTOMER.DELETE')")
    @PrivilegeDescription("Privilege to delete customer")
    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        return ok().build();
    }
}

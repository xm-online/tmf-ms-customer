package com.icthh.xm.tmf.ms.customer.web.rest;

import com.icthh.xm.commons.tenant.TenantContextHolder;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.model.Customer;
import com.icthh.xm.tmf.ms.customer.model.CustomerCreate;
import com.icthh.xm.tmf.ms.customer.model.PatchOperation;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Primary
@Component
public class DefaultCustomerApiDelegate implements CustomerApiDelegate {

    private final CustomerApiDelegateProvider tenantCustomerDelegateProvider;
    private final TenantContextHolder tenantContextHolder;

    public DefaultCustomerApiDelegate(CustomerApiDelegateProvider tenantCustomerDelegateProvider,
                                      TenantContextHolder tenantContextHolder) {
        this.tenantCustomerDelegateProvider = tenantCustomerDelegateProvider;
        this.tenantContextHolder = tenantContextHolder;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(CustomerCreate customer) {
        String tenantKey = tenantContextHolder.getTenantKey();
        return tenantCustomerDelegateProvider.selectBy(tenantKey, getProfile()).createCustomer(customer);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        String tenantKey = tenantContextHolder.getTenantKey();
        return tenantCustomerDelegateProvider.selectBy(tenantKey, getProfile()).deleteCustomer(id);
    }

    @Override
    public ResponseEntity<List<Customer>> listCustomer(String fields, Integer offset, Integer limit) {
        String tenantKey = tenantContextHolder.getTenantKey();
        return tenantCustomerDelegateProvider.selectBy(tenantKey, getProfile()).listCustomer(fields, offset, limit);
    }

    @Override
    public ResponseEntity<Customer> patchCustomer(String id, List<PatchOperation> operations) {
        String tenantKey = tenantContextHolder.getTenantKey();
        return tenantCustomerDelegateProvider.selectBy(tenantKey, getProfile()).patchCustomer(id, operations);
    }

    @Override
    public ResponseEntity<List<Customer>> retrieveCustomer(String id, String profile, String fields) {
        String tenantKey = tenantContextHolder.getTenantKey();
        return tenantCustomerDelegateProvider.selectBy(tenantKey, profile).retrieveCustomer(id, profile, fields);
    }

    private String getProfile() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Profile");
    }


}

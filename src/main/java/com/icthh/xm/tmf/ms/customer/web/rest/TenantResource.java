package com.icthh.xm.tmf.ms.customer.web.rest;

import com.icthh.xm.commons.gen.api.TenantsApiDelegate;
import com.icthh.xm.commons.gen.model.Tenant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TenantResource implements TenantsApiDelegate {

    @Override
    public ResponseEntity<Void> addTenant(Tenant body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTenant(String tenantKey) {
        return null;
    }

    @Override
    public ResponseEntity<List<Tenant>> getAllTenantInfo() {
        return null;
    }

    @Override
    public ResponseEntity<Tenant> getTenant(String tenantKey) {
        return null;
    }

    @Override
    public ResponseEntity<Void> manageTenant(String tenantKey, String body) {
        return null;
    }
}

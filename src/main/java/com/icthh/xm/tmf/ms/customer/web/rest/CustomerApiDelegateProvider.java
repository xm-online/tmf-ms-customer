package com.icthh.xm.tmf.ms.customer.web.rest;


import com.icthh.xm.tmf.ms.customer.SpringCustomerApiDelegate;
import com.icthh.xm.tmf.ms.customer.api.CustomerApiDelegate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerApiDelegateProvider {

    private final Map<String, Map<String, CustomerApiDelegate>> delegatesMap;
    private final LepCustomerDelegate lepCustomerDelegate;

    // XM:
    //  LANGUAGE: springBeanlanguage
    //  BONUSES: springBeaBonuses

    public CustomerApiDelegateProvider(List<SpringCustomerApiDelegate> customerApiDelegates,
                                       LepCustomerDelegate lepCustomerDelegate) {
        this.delegatesMap = new HashMap<>(customerApiDelegates.size());
        customerApiDelegates.forEach(delegate -> {
            String tenant = delegate.getTenant();
            final boolean tenantExists = delegatesMap.containsKey(tenant);
            final Map<String, CustomerApiDelegate> tenantServices =
                    tenantExists ? delegatesMap.get(tenant) : new HashMap<>();
            tenantServices.put(delegate.getProfile(), delegate);
            if (!tenantExists) {
                delegatesMap.put(tenant, tenantServices);
            }
        });
        this.lepCustomerDelegate = lepCustomerDelegate;
    }

    public CustomerApiDelegate selectBy(String tenant, String profile) {
        return delegatesMap.getOrDefault(tenant, Collections.emptyMap()).getOrDefault(profile, lepCustomerDelegate);
    }

}

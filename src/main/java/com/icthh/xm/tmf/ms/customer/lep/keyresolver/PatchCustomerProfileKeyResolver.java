package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.lep.api.LepKey;
import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * Delegates resolve to ProfileKeyResolver if 'profile' was found.
 * <p>
 *
 * @see ProfileKeyResolver
 */
@Component
@RequiredArgsConstructor
public class PatchCustomerProfileKeyResolver implements LepKeyResolver {

    private final ProfileKeyResolver profileKeyResolver;

    @Override
    public LepKey resolve(LepKey baseKey, LepMethod method, LepManagerService managerService) {
        if (!StringUtils.isBlank(profileKeyResolver.getProfile(method))) {
            return profileKeyResolver.resolve(baseKey, method, managerService);
        }
        return baseKey;
    }

}

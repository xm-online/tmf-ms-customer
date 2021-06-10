package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import static java.util.Optional.ofNullable;

import com.icthh.xm.lep.api.LepKey;
import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String profile = ofNullable(request.getHeader("Profile"))
                .orElseGet(String::new);
        if (!StringUtils.isBlank(profile)) {
            return profileKeyResolver.resolve(baseKey, method, managerService);
        }
        return baseKey;
    }

}

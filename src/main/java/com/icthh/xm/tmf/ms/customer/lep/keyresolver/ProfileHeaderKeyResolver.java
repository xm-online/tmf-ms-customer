package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.lep.api.LepKey;
import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static java.util.Optional.ofNullable;


/**
 * Delegates resolve to ProfileKeyResolver if 'profile' was found.
 * <p>
 *
 * @see ProfileKeyResolver
 */
@Component
@RequiredArgsConstructor
public class ProfileHeaderKeyResolver implements LepKeyResolver {

    private final ProfileKeyResolver profileKeyResolver;

    @Override
    public LepKey resolve(LepKey baseKey, LepMethod method, LepManagerService managerService) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String profile = ofNullable(request.getHeader("Profile"))
                .orElse(Strings.EMPTY);
        if (StringUtils.isNotBlank(profile)) {
            return profileKeyResolver.resolve(baseKey, method, managerService);
        }
        return baseKey;
    }

}

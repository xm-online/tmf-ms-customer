package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import static java.util.Optional.ofNullable;

import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepMethod;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Delegates resolve to ProfileKeyResolver if 'profile' was found.
 * <p>
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProfileHeaderKeyResolver implements LepKeyResolver {

    private final ProfileKeyResolver profileKeyResolver;

    @Override
    public List<String> segments(LepMethod method) {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String profile = ofNullable(request.getHeader("Profile"))
            .orElse(Strings.EMPTY);
        if (StringUtils.isBlank(profile)) {
            return List.of(Strings.EMPTY);
        }
        return profileKeyResolver.segments(method);
    }
}

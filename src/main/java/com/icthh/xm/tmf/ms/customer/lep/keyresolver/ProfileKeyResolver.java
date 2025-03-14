package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepMethod;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ProfileKeyResolver implements LepKeyResolver {

    @Override
    public List<String> segments(LepMethod method) {
        String profile = getProfile(method);
        return List.of(Optional.ofNullable(profile).orElse(EMPTY));
    }

    private String getProfile(LepMethod method) {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String profile = ofNullable(request.getHeader("Profile"))
            .or(() -> ofNullable(method.getParameter("profile", String.class)))
            .orElse(Strings.EMPTY);
        log.info("getProfile: result: {}", profile);
        return profile;
    }
}

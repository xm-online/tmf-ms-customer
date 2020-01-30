package com.icthh.xm.tmf.ms.customer.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.icthh.xm.tmf.ms.customer.config.Constants.SYSTEM_ACCOUNT;
import static com.icthh.xm.tmf.ms.customer.security.SecurityUtils.getCurrentUserLogin;
import static java.util.Optional.of;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return of(getCurrentUserLogin()
            .orElse(SYSTEM_ACCOUNT));
    }
}

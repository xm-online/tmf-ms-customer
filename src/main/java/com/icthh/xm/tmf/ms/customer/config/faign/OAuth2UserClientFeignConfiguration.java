package com.icthh.xm.tmf.ms.customer.config.faign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import static java.lang.String.format;

public class OAuth2UserClientFeignConfiguration {

    @Bean(name = "userFeignClientInterceptor")
    public RequestInterceptor getUserFeignClientInterceptor() {
        return new UserFeignClientInterceptor();
    }

    class UserFeignClientInterceptor implements RequestInterceptor {

        private static final String AUTHORIZATION_HEADER = "Authorization";
        private static final String BEARER_TOKEN_TYPE = "Bearer";

        @Override
        public void apply(RequestTemplate template) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();

            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {

                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                template.header(AUTHORIZATION_HEADER, format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
            }
        }
    }
}

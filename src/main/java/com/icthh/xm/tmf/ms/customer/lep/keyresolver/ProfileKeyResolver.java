package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.commons.lep.AppendLepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.MethodSignature;
import com.icthh.xm.lep.api.commons.SeparatorSegmentedLepKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class ProfileKeyResolver extends AppendLepKeyResolver {

    @Override
    protected String[] getAppendSegments(SeparatorSegmentedLepKey baseKey,
                                         LepMethod method,
                                         LepManagerService managerService) {
        String profile = getProfile(method);
        String translated = translateToLepConvention(profile);
        return new String[]{translated};
    }

    private String getProfile(LepMethod method) {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String profile = ofNullable(request.getHeader("Profile"))
            .or(() -> ofNullable(getParamValue(method, "profile", String.class)))
            .orElse(Strings.EMPTY);
        log.info("getProfile: result: {}", profile);
        return profile;
    }

    @Override
    protected <T> T getRequiredParam(LepMethod method, String paramName, Class<T> valueType) {
        return ofNullable(getParamValue(method, paramName, valueType))
            .or(() -> getHeaderValue(paramName, valueType))
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("Neither LEP method %s nor request header contain parameter %s",
                    getMethodDescription(method), paramName)));
    }

    private <T> Optional<T> getHeaderValue(final String headerName, final Class<T> valueType) {
        return ofNullable(RequestContextHolder.getRequestAttributes())
            .map(ServletRequestAttributes.class::cast)
            .map(ServletRequestAttributes::getRequest)
            .map(request -> request.getHeader(headerName))
            .map(valueType::cast);
    }

    private String getMethodDescription(LepMethod method) {
        return ofNullable(method.getMethodSignature())
            .map(MethodSignature::getMethod)
            .map(Method::toString)
            .orElseGet(method::toString);
    }
}

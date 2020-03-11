package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.commons.lep.AppendLepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.MethodSignature;
import com.icthh.xm.lep.api.commons.SeparatorSegmentedLepKey;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class ProfileKeyResolver extends AppendLepKeyResolver {

    @Override
    protected String[] getAppendSegments(SeparatorSegmentedLepKey baseKey,
                                         LepMethod method,
                                         LepManagerService managerService) {
        String profile = getRequiredParam(method, "profile", String.class);
        String translated = translateToLepConvention(profile);
        return new String[]{translated};
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
        return ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .map(attributes -> attributes.getRequest().getHeader(headerName))
            .map(valueType::cast);
    }

    private String getMethodDescription(LepMethod method) {
        MethodSignature methodSignature = method.getMethodSignature();
        if (methodSignature != null && methodSignature.getMethod() != null) {
            return methodSignature.getMethod().toString();
        }
        return method.toString();
    }
}

package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.commons.lep.AppendLepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.commons.SeparatorSegmentedLepKey;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ProfileKeyResolver extends AppendLepKeyResolver {

    @Override
    protected String[] getAppendSegments(SeparatorSegmentedLepKey baseKey,
                                         LepMethod method,
                                         LepManagerService managerService) {
        String profile = getOptionalParam(method, "profile", String.class);

        final String[] translatedProfile = new String[1];

        if (isNull(profile))
            return translatedProfile;

        translatedProfile[0] = translateToLepConvention(profile);

        return translatedProfile;
    }

    protected <T> T getOptionalParam(LepMethod method, String paramName, Class<T> valueType) {
        T value = getParamValue(method, paramName, valueType);
        return isNull(value) ? null : value;
    }
}

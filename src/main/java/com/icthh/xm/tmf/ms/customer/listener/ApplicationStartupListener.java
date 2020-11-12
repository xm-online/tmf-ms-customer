package com.icthh.xm.tmf.ms.customer.listener;

import com.icthh.xm.commons.logging.util.MdcUtils;
import com.icthh.xm.commons.permission.inspector.PrivilegeInspector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final PrivilegeInspector privilegeInspector;

    @Value("${xm-config.enabled}")
    private boolean xmConfigEnabled;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (xmConfigEnabled) {
            privilegeInspector.readPrivileges(MdcUtils.getRid());
        } else {
            log.warn("WARNING! Privileges inspection is disabled by "
                + "configuration parameter 'xm-config.enabled'");
        }
    }
}

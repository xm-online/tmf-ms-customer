package com.icthh.xm.tmf.ms.customer.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestHeaderExtractor {

    public static String getHeader(String headerName) {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(headerName);
    }
}

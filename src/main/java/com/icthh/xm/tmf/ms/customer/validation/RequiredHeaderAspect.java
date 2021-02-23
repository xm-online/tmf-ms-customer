package com.icthh.xm.tmf.ms.customer.validation;

import com.icthh.xm.commons.exceptions.BusinessException;
import java.lang.annotation.Annotation;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP handler for {@link RequiredHeader}
 * @author maximBogun
 */
@Component
@Aspect
public class RequiredHeaderAspect {

    @Before(value = "@annotation(RequiredHeader)")
    public void before(JoinPoint thisJoinPoint) {

        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();

        for (Annotation annotation : signature.getMethod().getAnnotations()) {

            if (annotation.annotationType() == RequiredHeader.class) {

                RequiredHeader requiredHeader = (RequiredHeader) annotation;

                HttpServletRequest request =
                    ((ServletRequestAttributes) Objects.requireNonNull(
                        RequestContextHolder.getRequestAttributes(), "Current request is null")).getRequest();

                for (String header : requiredHeader.values()) {

                    if (request.getHeader(header) == null) {
                        throw new BusinessException(String.format("Required header not found : %s", header));
                    }

                }

            }

        }

    }
}

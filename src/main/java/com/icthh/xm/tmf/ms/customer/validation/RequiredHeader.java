package com.icthh.xm.tmf.ms.customer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validate current request on containing headers
 * @author maximBougun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredHeader {
    /**
     * @return arrays of headers
     */
    String[] values() default {};
}

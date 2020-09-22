package com.icthh.xm.tmf.ms.customer.util;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import java.util.Arrays;
import java.util.Collection;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static Collection<String> toStringList(String fields) {
        return ofNullable(fields)
            .map(f -> f.split(",", -1))
            .map(Arrays::asList)
            .orElse(emptyList());
    }
}

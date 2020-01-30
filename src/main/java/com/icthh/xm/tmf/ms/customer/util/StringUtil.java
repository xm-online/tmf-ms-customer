package com.icthh.xm.tmf.ms.customer.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class StringUtil {

    public static Collection<String> toStringList(String fields) {
        return Stream.of(fields.split(",", -1))
            .collect(toList());
    }
}

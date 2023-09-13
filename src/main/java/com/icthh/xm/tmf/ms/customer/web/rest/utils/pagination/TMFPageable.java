package com.icthh.xm.tmf.ms.customer.web.rest.utils.pagination;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface TMFPageable extends Pageable, Serializable {

    int getLimit();

    long getOffset();
}

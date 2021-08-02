package com.icthh.xm.tmf.ms.customer.web.rest.util.pagination;

import java.io.Serializable;
import org.springframework.data.domain.Pageable;

public interface TMFPageable extends Pageable, Serializable {

    int getLimit();

    long getOffset();
}

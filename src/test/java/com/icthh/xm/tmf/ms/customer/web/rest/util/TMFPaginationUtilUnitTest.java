package com.icthh.xm.tmf.ms.customer.web.rest.util;

import static org.junit.Assert.*;

import com.icthh.xm.tmf.ms.customer.web.rest.util.pagination.OffsetLimitPageRequest;
import com.icthh.xm.tmf.ms.customer.web.rest.util.pagination.TMFPaginationUtil;
import io.github.jhipster.web.util.PaginationUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;

/**
 * Tests based on parsing algorithm in app/components/util/pagination-util.service.js
 *
 * @see PaginationUtil
 */
public class TMFPaginationUtilUnitTest {

    @Test
    public void generatePaginationHttpHeadersTest() {
        String baseUrl = "/api/_search/example";
        List<String> content = new ArrayList<>();
        Page<String> page = new PageImpl<>(content, new OffsetLimitPageRequest(300, 50), 400L);
        HttpHeaders headers = TMFPaginationUtil.generatePaginationHttpHeaders(page, baseUrl);
        List<String> strHeaders = headers.get(HttpHeaders.LINK);
        assertNotNull(strHeaders);
        assertTrue(strHeaders.size() == 1);
        String headerData = strHeaders.get(0);
        assertTrue(headerData.split(",").length == 5);
        String expectedData =
                "</api/_search/example?limit=50&offset=300>; rel=\"self\","
                        + "</api/_search/example?limit=50&offset=350>; rel=\"next\","
                        + "</api/_search/example?limit=50&offset=250>; rel=\"prev\","
                        + "</api/_search/example?limit=50&offset=350>; rel=\"last\","
                        + "</api/_search/example?limit=50&offset=0>; rel=\"first\"";
        assertEquals(expectedData, headerData);
        List<String> xTotalCountHeaders = headers.get("X-Total-Count");
        assertTrue(xTotalCountHeaders.size() == 1);
        assertTrue(Long.valueOf(xTotalCountHeaders.get(0)).equals(400L));
    }

}

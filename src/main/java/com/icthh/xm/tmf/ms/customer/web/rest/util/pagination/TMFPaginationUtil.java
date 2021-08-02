package com.icthh.xm.tmf.ms.customer.web.rest.util.pagination;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Utility class for handling pagination.
 * <p>
 * For query-based pagination, the server MUST return next HTTP headers:
 * <p>
 * “X-Total-Count”, with the total number of matching resources so that the client can calculate the next page
 * "Link", with relations [self,first,next,prev,last] and relative urls to each of them, "next" and "prev" are optional (if no data)
 */
public final class TMFPaginationUtil {

    private static final String X_TOTAL_COUNT_HEADER_NAME = "X-Total-Count";

    private TMFPaginationUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Not for instantiation!");
    }

    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page, String baseUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT_HEADER_NAME, Long.toString(page.getTotalElements()));
        String link = "";

        //self
        link += "<" + generateUri(baseUrl, page.getNumber(), page.getSize()) + ">; rel=\"self\",";

        //next
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link += "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String generateUri(String baseUrl, int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("limit", size).queryParam("offset", size * page).toUriString();
    }
}

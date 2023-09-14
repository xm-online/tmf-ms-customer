package com.icthh.xm.tmf.ms.customer.web.rest.util;

import com.icthh.xm.commons.exceptions.EntityNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseContentUtils {

    private ResponseContentUtils() {
    }

    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status, or if it's empty,
     * it returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }

    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status with the headers,
     * or if it's empty, it returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @param header        headers to be added to the response
     * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
            .orElseThrow(() -> new EntityNotFoundException("Can not find entity by ID"));
    }

    /**
     * Wraps value int the {@link ResponseEntity}.
     *
     * @param value value to wrap
     * @param <T>   type of the value to wrap
     * @return if value not {@code null} returns the {@link ResponseEntity} with value in body or throws
     * {@link EntityNotFoundException} if value is {@code null}
     */
    public static <T> ResponseEntity<T> wrapOrNotFound(T value) {
        return wrapOrNotFound(Optional.ofNullable(value));
    }

}

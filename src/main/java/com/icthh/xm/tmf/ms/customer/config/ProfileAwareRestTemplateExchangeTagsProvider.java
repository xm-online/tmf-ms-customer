package com.icthh.xm.tmf.ms.customer.config;

import io.micrometer.core.instrument.Tag;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.springframework.boot.actuate.metrics.web.client.RestTemplateExchangeTags;
import org.springframework.boot.actuate.metrics.web.client.RestTemplateExchangeTagsProvider;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class ProfileAwareRestTemplateExchangeTagsProvider implements RestTemplateExchangeTagsProvider {

    @Override
    public Iterable<Tag> getTags(String urlTemplate, HttpRequest request, ClientHttpResponse response) {
        String profileValue = Optional.of(request.getHeaders())
            .map(headers -> headers.get("Profile"))
            .orElse(Collections.emptyList())
            .stream()
            .findFirst()
            .orElse("none");
        return Arrays.asList(RestTemplateExchangeTags.method(request), Tag.of("profile", profileValue),
            RestTemplateExchangeTags.status(response), RestTemplateExchangeTags.clientName(request));
    }

}

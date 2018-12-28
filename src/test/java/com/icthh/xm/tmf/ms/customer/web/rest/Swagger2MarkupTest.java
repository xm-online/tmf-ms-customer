package com.icthh.xm.tmf.ms.customer.web.rest;

import io.github.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Swagger2MarkupTest {

    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
        Path outputDirectory = Paths.get("build/asciidoc");
        InputStream swaggerStream = getClass().getClassLoader().getResourceAsStream("swagger/api.yml");

        Swagger2MarkupConverter.from(new BufferedReader(new InputStreamReader(swaggerStream)))
            .build()
            .toFolder(outputDirectory);
    }
}

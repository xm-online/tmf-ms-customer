package com.icthh.xm.ms.mstemplate.security.access;

import com.icthh.xm.commons.permission.access.AbstractResourceFactory;
import com.icthh.xm.commons.permission.access.repository.ResourceRepository;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntityFirstRepository;
import com.icthh.xm.ms.mstemplate.repository.ExampleEntitySecondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateResourceFactory extends AbstractResourceFactory {

    private final ExampleEntityFirstRepository exampleEntityFirstRepository;
    private final ExampleEntitySecondRepository exampleEntitySecondRepository;

    @Override
    protected Map<String, ? extends ResourceRepository> getRepositories() {
        return Map.of(
                "exampleEntityFirst", exampleEntityFirstRepository,
                "exampleEntitySecond", exampleEntitySecondRepository
        );
    }
}

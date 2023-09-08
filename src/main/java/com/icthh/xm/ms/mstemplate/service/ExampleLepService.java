package com.icthh.xm.ms.mstemplate.service;

import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@LepService(group = "example")
public class ExampleLepService {
    @LogicExtensionPoint(value = "ExampleLepMethod")
    public String exampleLepMethod(String testInput) {
        log.warn("Called original method {}", testInput);
        return testInput;
    }

}


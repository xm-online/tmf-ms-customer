package com.icthh.xm.ms.mstemplate.web.rest.api;

import com.codahale.metrics.annotation.Timed;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.ms.mstemplate.service.ExampleLepService;
import com.icthh.xm.ms.mstemplate.service.api.dto.ExecuteRequest;
import com.icthh.xm.ms.mstemplate.service.api.dto.ExecuteResponse;
import com.icthh.xm.ms.mstemplate.web.api.ExampleLepFunctionApiFirstApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleLepResource implements ExampleLepFunctionApiFirstApiDelegate {

    private final ExampleLepService exampleLepService;

    @Timed
    @PreAuthorize("hasPermission({'executeRequest': #executeRequest}, 'FUNCTION.EXECUTE')")
    @Override
    @PrivilegeDescription("Privilege to create porting request")
    public ResponseEntity<ExecuteResponse> executeLepFunction(ExecuteRequest executeRequest) {
        return ResponseEntity.ok(new ExecuteResponse()
                .testOutput(exampleLepService.exampleLepMethod(executeRequest.getTestInput())));
    }
}

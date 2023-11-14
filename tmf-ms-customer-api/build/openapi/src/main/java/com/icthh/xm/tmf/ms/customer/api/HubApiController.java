package com.icthh.xm.tmf.ms.customer.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
@Controller
@RequestMapping("${openapi.customer.base-path:/api/customerManagement/v3}")
public class HubApiController implements HubApi {

    private final HubApiDelegate delegate;

    public HubApiController(@Autowired(required = false) HubApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new HubApiDelegate() {});
    }

    @Override
    public HubApiDelegate getDelegate() {
        return delegate;
    }

}

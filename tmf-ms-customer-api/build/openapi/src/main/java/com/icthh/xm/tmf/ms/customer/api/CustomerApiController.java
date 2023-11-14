package com.icthh.xm.tmf.ms.customer.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
@Controller
@RequestMapping("${openapi.customer.base-path:/api/customerManagement/v3}")
public class CustomerApiController implements CustomerApi {

    private final CustomerApiDelegate delegate;

    public CustomerApiController(@Autowired(required = false) CustomerApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CustomerApiDelegate() {});
    }

    @Override
    public CustomerApiDelegate getDelegate() {
        return delegate;
    }

}

package com.icthh.xm.tmf.ms.customer.api.v31;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
@Controller
@RequestMapping("${openapi.customer.base-path:/api/customerManagement/v3.1}")
public class CustomerV31ApiController implements CustomerV31Api {

    private final CustomerV31ApiDelegate delegate;

    public CustomerV31ApiController(@Autowired(required = false) CustomerV31ApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CustomerV31ApiDelegate() {});
    }

    @Override
    public CustomerV31ApiDelegate getDelegate() {
        return delegate;
    }

}

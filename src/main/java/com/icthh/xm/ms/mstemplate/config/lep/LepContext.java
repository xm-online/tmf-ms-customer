package com.icthh.xm.ms.mstemplate.config.lep;


import com.icthh.xm.commons.lep.BaseProceedingLep;
import com.icthh.xm.commons.lep.spring.LepThreadHelper;
import com.icthh.xm.commons.lep.spring.lepservice.LepServiceFactory;
import com.icthh.xm.commons.logging.trace.TraceService;
import com.icthh.xm.commons.security.XmAuthenticationContext;
import com.icthh.xm.commons.tenant.TenantContext;

public class LepContext {

    public Object commons;
    public Object inArgs;
    public BaseProceedingLep lep;
    public LepThreadHelper thread;
    public XmAuthenticationContext authContext;
    public TenantContext tenantContext;
    public Object methodResult;
    public LepServiceFactory lepServices;
    public TraceService traceService;

}


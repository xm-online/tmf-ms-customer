package com.icthh.xm.tmf.ms.customer.lep.keyresolver;

import com.icthh.xm.commons.lep.XmLepConstants;
import com.icthh.xm.commons.lep.spring.LepServiceHandler;
import com.icthh.xm.lep.api.LepKey;
import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepManager;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.Version;
import com.icthh.xm.lep.core.CoreLepManager;
import com.icthh.xm.tmf.ms.customer.service.CustomerService;
import com.icthh.xm.tmf.ms.customer.web.rest.CustomerDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileKeyResolverUnitTest {

    private static final String PROFILE_KEY = "profile";
    private static final String PROFILE_VALUE = "TEST-PROFILE";
    private static final String PROFILE_VALUE_RESOLVED = "TEST_PROFILE";

    @InjectMocks
    private LepServiceHandler lepServiceHandler;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private CoreLepManager lepManager;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<LepKey> baseLepKey;

    @Captor
    private ArgumentCaptor<LepKeyResolver> keyResolver;

    @Captor
    private ArgumentCaptor<LepMethod> lepMethod;

    @Captor
    private ArgumentCaptor<Version> version;

    @Test
    public void shouldResolveLepByMethodArg() throws Throwable {

        Method method = CustomerDelegate.class.getMethod("retrieveCustomer", String.class,
            String.class, String.class);

        when(applicationContext.getBean(LepManager.class)).thenReturn(lepManager);

        ProfileKeyResolver resolver = new ProfileKeyResolver();
        when(applicationContext.getBean(ProfileKeyResolver.class)).thenReturn(resolver);

        lepServiceHandler.onMethodInvoke(CustomerDelegate.class,
            new CustomerDelegate(customerService), method, new Object[]{null, PROFILE_VALUE, null});

        verify(lepManager)
            .processLep(baseLepKey.capture(), version.capture(), keyResolver.capture(), lepMethod.capture());

        LepKey resolvedKey = resolver.resolve(baseLepKey.getValue(), lepMethod.getValue(), null);

        assertEquals(
            String.join(XmLepConstants.EXTENSION_KEY_SEPARATOR,
                "service", "RetrieveCustomer", PROFILE_VALUE_RESOLVED), resolvedKey.getId());
    }

    @Test
    public void shouldResolveLepByHeader() throws Throwable {

        Method method = CustomerDelegate.class.getMethod("retrieveCustomer", String.class,
            String.class, String.class);

        when(applicationContext.getBean(LepManager.class)).thenReturn(lepManager);

        ProfileKeyResolver resolver = new ProfileKeyResolver();
        when(applicationContext.getBean(ProfileKeyResolver.class)).thenReturn(resolver);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(PROFILE_KEY, PROFILE_VALUE);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        lepServiceHandler.onMethodInvoke(CustomerDelegate.class,
            new CustomerDelegate(customerService), method, new Object[]{null, null, null});

        verify(lepManager)
            .processLep(baseLepKey.capture(), version.capture(), keyResolver.capture(), lepMethod.capture());

        LepKey resolvedKey = resolver.resolve(baseLepKey.getValue(), lepMethod.getValue(), null);

        assertEquals(
            String.join(XmLepConstants.EXTENSION_KEY_SEPARATOR,
                "service", "RetrieveCustomer", PROFILE_VALUE_RESOLVED), resolvedKey.getId());
    }
}

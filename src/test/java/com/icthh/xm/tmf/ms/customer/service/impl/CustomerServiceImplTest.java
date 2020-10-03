package com.icthh.xm.tmf.ms.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.icthh.xm.commons.exceptions.BusinessException;
import com.icthh.xm.tmf.ms.customer.domain.properties.CustomerCharacteristics;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CustomerServiceImplTest {

    private static final String LANGUAGE = "language";
    private static final CustomerCharacteristics.Characteristic languageConfig =
        new CustomerCharacteristics.Characteristic()
        .setKeyRegexp(LANGUAGE)
        .setDefaultValue("ua")
        .setPredefinedValues(List.of("ua", "ru", "en"));

    private CustomerConfigurationServiceImpl customerConfigService = mock(CustomerConfigurationServiceImpl.class);
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setup() {
        customerService = new CustomerServiceImpl(
            null, customerConfigService, null);
    }

    private static Stream<Arguments> paramsToMatch() {
        return Stream.of(
            Arguments.of("Should match predefined values", new Characteristic()
                    .name(LANGUAGE).value("ru"),
                languageConfig),
            Arguments.of("Should match keyRegexp ", new Characteristic()
                    .name("lang-code").value("some-code"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp("lang(.*)")
            ),
            Arguments.of("Should match value regexp", new Characteristic()
                    .name(LANGUAGE).value("regexp"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setRegexp("reg(.*)")
            ),
            Arguments.of("Should match min length", new Characteristic()
                    .name(LANGUAGE).value("123"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setMinLength(2)
            ),
            Arguments.of("Should match max length", new Characteristic()
                    .name(LANGUAGE).value("1"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setMaxLength(2)
            )
        );
    }

    private static Stream<Arguments> paramsNotToMatch() {
        return Stream.of(
            Arguments.of("Should not match not in predefined values", new Characteristic()
                    .name(LANGUAGE).value("dummy"),
                languageConfig),
            Arguments.of("Should not match by keyRegexp", new Characteristic()
                    .name("dummy").value("some-code"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp("lang(.*)")
            ),
            Arguments.of("Should not match by value regexp", new Characteristic()
                    .name(LANGUAGE).value("dummy"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setRegexp("reg(.*)")
            ),
            Arguments.of("Should not match min length", new Characteristic()
                    .name(LANGUAGE).value("1"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setMinLength(2)
            ),
            Arguments.of("Should not match max length", new Characteristic()
                    .name(LANGUAGE).value("123"),
                new CustomerCharacteristics.Characteristic()
                    .setKeyRegexp(LANGUAGE)
                    .setMaxLength(2)
            )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("paramsToMatch")
    void shouldValidateSuccessfully(String description, Characteristic characteristic,
                                    CustomerCharacteristics.Characteristic config) {
        //given
        prepareConfigMock(config);

        //when
        CustomerCharacteristics.Characteristic result =
            customerService.validateCharacteristic(characteristic);

        //then
        assertNotNull(result); //not null result means validation passed
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("paramsNotToMatch")
    void shouldValidateWithException(String description, Characteristic characteristic,
                                     CustomerCharacteristics.Characteristic config) {
        prepareConfigMock(config);

        try {
            customerService.validateCharacteristic(characteristic);
            fail();
        } catch (BusinessException e) {
            assertEquals("error.characteristic.invalid", e.getCode());
            assertEquals("Invalid characteristic " + characteristic.getName(), e.getMessage());
        }
    }

    private void prepareConfigMock(CustomerCharacteristics.Characteristic characteristic) {
        when(customerConfigService.getConfig()).thenReturn(new CustomerCharacteristics()
            .setCharacteristics(List.of(characteristic)));
    }
}

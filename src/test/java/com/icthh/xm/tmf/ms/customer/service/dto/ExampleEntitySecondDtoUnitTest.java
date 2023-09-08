package com.icthh.xm.tmf.ms.customer.service.dto;

import com.icthh.xm.tmf.ms.customer.AbstractUnitTest;
import com.icthh.xm.tmf.ms.customer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleEntitySecondDtoUnitTest extends AbstractUnitTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExampleEntitySecondDto.class);
        ExampleEntitySecondDto exampleEntitySecondDto1 = new ExampleEntitySecondDto();
        exampleEntitySecondDto1.setId(1L);
        ExampleEntitySecondDto exampleEntitySecondDto2 = new ExampleEntitySecondDto();
        assertThat(exampleEntitySecondDto1).isNotEqualTo(exampleEntitySecondDto2);
        exampleEntitySecondDto2.setId(exampleEntitySecondDto1.getId());
        assertThat(exampleEntitySecondDto1).isEqualTo(exampleEntitySecondDto2);
        exampleEntitySecondDto2.setId(2L);
        assertThat(exampleEntitySecondDto1).isNotEqualTo(exampleEntitySecondDto2);
        exampleEntitySecondDto1.setId(null);
        assertThat(exampleEntitySecondDto1).isNotEqualTo(exampleEntitySecondDto2);
    }
}

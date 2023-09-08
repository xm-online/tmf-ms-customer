package com.icthh.xm.tmf.ms.customer.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.icthh.xm.tmf.ms.customer.AbstractUnitTest;
import com.icthh.xm.tmf.ms.customer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ExampleEntityFirstDtoUnitTest extends AbstractUnitTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExampleEntityFirstDto.class);
        ExampleEntityFirstDto exampleEntityFirstDto1 = new ExampleEntityFirstDto();
        exampleEntityFirstDto1.setId(1L);
        ExampleEntityFirstDto exampleEntityFirstDto2 = new ExampleEntityFirstDto();
        assertThat(exampleEntityFirstDto1).isNotEqualTo(exampleEntityFirstDto2);
        exampleEntityFirstDto2.setId(exampleEntityFirstDto1.getId());
        assertThat(exampleEntityFirstDto1).isEqualTo(exampleEntityFirstDto2);
        exampleEntityFirstDto2.setId(2L);
        assertThat(exampleEntityFirstDto1).isNotEqualTo(exampleEntityFirstDto2);
        exampleEntityFirstDto1.setId(null);
        assertThat(exampleEntityFirstDto1).isNotEqualTo(exampleEntityFirstDto2);
    }
}

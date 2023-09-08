package com.icthh.xm.tmf.ms.customer.service.mapper;

import com.icthh.xm.tmf.ms.customer.domain.ExampleEntitySecond;
import com.icthh.xm.tmf.ms.customer.service.dto.ExampleEntitySecondDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ExampleEntitySecond} and its DTO {@link ExampleEntitySecondDto}.
 */
@Mapper(componentModel = "spring")
public interface ExampleEntitySecondMapper extends EntityMapper<ExampleEntitySecondDto, ExampleEntitySecond> {}

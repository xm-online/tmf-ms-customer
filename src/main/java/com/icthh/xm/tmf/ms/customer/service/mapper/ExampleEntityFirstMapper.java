package com.icthh.xm.tmf.ms.customer.service.mapper;

import com.icthh.xm.tmf.ms.customer.domain.ExampleEntityFirst;
import com.icthh.xm.tmf.ms.customer.domain.ExampleEntitySecond;
import com.icthh.xm.tmf.ms.customer.service.dto.ExampleEntityFirstDto;
import com.icthh.xm.tmf.ms.customer.service.dto.ExampleEntitySecondDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link ExampleEntityFirst} and its DTO {@link ExampleEntityFirstDto}.
 */
@Mapper(componentModel = "spring")
public interface ExampleEntityFirstMapper extends EntityMapper<ExampleEntityFirstDto, ExampleEntityFirst> {
    @Mapping(target = "exampleEntitySecond", source = "exampleEntitySecond", qualifiedByName = "exampleEntitySecondId")
    ExampleEntityFirstDto toDto(ExampleEntityFirst s);

    @Named("exampleEntitySecondId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExampleEntitySecondDto toDtoExampleEntitySecondId(ExampleEntitySecond exampleEntitySecond);
}

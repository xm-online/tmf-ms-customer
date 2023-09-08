package com.icthh.xm.ms.mstemplate.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntityFirst} entity.
 */
public class ExampleEntityFirstDto implements Serializable {

    private Long id;

    private String name;

    private ExampleEntitySecondDto exampleEntitySecond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExampleEntitySecondDto getExampleEntitySecond() {
        return exampleEntitySecond;
    }

    public void setExampleEntitySecond(ExampleEntitySecondDto exampleEntitySecond) {
        this.exampleEntitySecond = exampleEntitySecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExampleEntityFirstDto)) {
            return false;
        }

        ExampleEntityFirstDto exampleEntityFirstDto = (ExampleEntityFirstDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, exampleEntityFirstDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExampleEntityFirstDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", exampleEntitySecond=" + getExampleEntitySecond() +
            "}";
    }
}

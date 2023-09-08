package com.icthh.xm.tmf.ms.customer.service.dto;

import com.icthh.xm.tmf.ms.customer.domain.ExampleEntitySecond;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ExampleEntitySecond} entity.
 */
public class ExampleEntitySecondDto implements Serializable {

    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExampleEntitySecondDto)) {
            return false;
        }

        ExampleEntitySecondDto exampleEntitySecondDto = (ExampleEntitySecondDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, exampleEntitySecondDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExampleEntitySecondDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

package com.icthh.xm.ms.mstemplate.service.criteria;

import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntityFirst} entity. This class is used
 * in {@link com.icthh.xm.ms.mstemplate.web.rest.ExampleEntityFirstResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /example-entity-firsts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ExampleEntityFirstCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter exampleEntitySecondId;

    public ExampleEntityFirstCriteria() {}

    public ExampleEntityFirstCriteria(ExampleEntityFirstCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.exampleEntitySecondId = other.exampleEntitySecondId == null ? null : other.exampleEntitySecondId.copy();
    }

    @Override
    public ExampleEntityFirstCriteria copy() {
        return new ExampleEntityFirstCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public LongFilter getExampleEntitySecondId() {
        return exampleEntitySecondId;
    }

    public LongFilter exampleEntitySecondId() {
        if (exampleEntitySecondId == null) {
            exampleEntitySecondId = new LongFilter();
        }
        return exampleEntitySecondId;
    }

    public void setExampleEntitySecondId(LongFilter exampleEntitySecondId) {
        this.exampleEntitySecondId = exampleEntitySecondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExampleEntityFirstCriteria that = (ExampleEntityFirstCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(exampleEntitySecondId, that.exampleEntitySecondId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, exampleEntitySecondId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExampleEntityFirstCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (exampleEntitySecondId != null ? "exampleEntitySecondId=" + exampleEntitySecondId + ", " : "") +
            "}";
    }
}

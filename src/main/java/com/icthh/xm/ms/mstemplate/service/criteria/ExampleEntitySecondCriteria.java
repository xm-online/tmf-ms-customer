package com.icthh.xm.ms.mstemplate.service.criteria;

import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.icthh.xm.ms.mstemplate.domain.ExampleEntitySecond} entity. This class is used
 * in {@link com.icthh.xm.ms.mstemplate.web.rest.ExampleEntitySecondResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /example-entity-seconds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ExampleEntitySecondCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    public ExampleEntitySecondCriteria() {}

    public ExampleEntitySecondCriteria(ExampleEntitySecondCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
    }

    @Override
    public ExampleEntitySecondCriteria copy() {
        return new ExampleEntitySecondCriteria(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExampleEntitySecondCriteria that = (ExampleEntitySecondCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExampleEntitySecondCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            "}";
    }
}

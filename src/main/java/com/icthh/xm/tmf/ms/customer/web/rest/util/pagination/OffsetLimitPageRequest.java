package com.icthh.xm.tmf.ms.customer.web.rest.util.pagination;

import java.util.Objects;
import java.util.StringJoiner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * <p>
 * For query based pagination, the following query parameters MUST be supported:
 * <p>
 * Parameter     Type        Description
 * offset        integer     Requested index for start of resources to be provided in response requested by client
 * limit         integer     Requested number of resources to be provided in response requested by client
 *
 * The above pagination query parameters support both getting a page in the middle of a resultset and the
 * capability to get the next page.
 * If the offset query parameter is missing then it must default to zero
 *
 * ?limit=20 Get the first twenty matching resources.
 * ?offset=0&limit=20 Get the first twenty matching resources.
 * ?offset=10&limit=20 Get the twenty resources starting at the tenth
 *
 * */
public class OffsetLimitPageRequest implements TMFPageable {

    private static final long serialVersionUID = -25822477129613575L;

    private int limit;
    private int offset;
    private final Sort sort;


    public OffsetLimitPageRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public OffsetLimitPageRequest(int offset, int limit) {
        this(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageRequest((int) getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetLimitPageRequest previous() {
        return hasPrevious() ? new OffsetLimitPageRequest((int) getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffsetLimitPageRequest that = (OffsetLimitPageRequest) o;
        return limit == that.limit &&
                offset == that.offset &&
                Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, offset, sort);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OffsetLimitPageRequest.class.getSimpleName() + "[", "]")
                .add("limit=" + limit)
                .add("offset=" + offset)
                .add("sort=" + sort)
                .toString();
    }
}

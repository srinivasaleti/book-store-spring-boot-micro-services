package com.srinivas.bookstore.catalog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PageResult<T> {

    private final List<T> data;
    private final int currentPage;
    private final long totalElements;
    private final int totalPages;
    private final boolean hasNext;
    private final boolean hasPrev;

    @JsonProperty("isFirst")
    private final boolean isFirst;

    @JsonProperty("isLast")
    private final boolean isLast;
}

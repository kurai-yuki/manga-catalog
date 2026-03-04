package com.manga.catalog.manga_catalog.dtos;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int page;

    private int pageSize;

    private String orderBy;

    private Direction orderDirection;
}

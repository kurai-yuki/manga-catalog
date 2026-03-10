package com.manga.catalog.manga_catalog.dtos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private Pageable pagination;

    public PaginationResponse(Page<T> page) {
        this.data = page.getContent();
        this.pagination = page.getPageable();
    }
}
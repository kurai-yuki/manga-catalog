package com.manga.catalog.manga_catalog.impl;

import java.util.List;

import com.manga.catalog.manga_catalog.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.dtos.PaginationResponse;

public interface IService<ResponseDto, CreateDto> {
    default PaginationResponse<ResponseDto> findAll(PaginationRequest pagination) {
        return null;
    };

    default List<ResponseDto> findAll() {
        return null;
    };

    ResponseDto findById(int id);

    ResponseDto add(CreateDto payload);

    ResponseDto update(int id, CreateDto payload);

    void remove(int id);
}
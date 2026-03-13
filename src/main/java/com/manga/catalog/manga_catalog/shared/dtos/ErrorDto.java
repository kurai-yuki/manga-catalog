package com.manga.catalog.manga_catalog.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private int statusCode;
}

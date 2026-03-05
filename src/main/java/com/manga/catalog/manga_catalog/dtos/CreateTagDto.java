package com.manga.catalog.manga_catalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTagDto {
    private String name;

    private String description;
}

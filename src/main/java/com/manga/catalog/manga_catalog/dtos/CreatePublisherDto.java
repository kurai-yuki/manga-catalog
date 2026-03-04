package com.manga.catalog.manga_catalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePublisherDto {
    private String name;

    private String description;

    private String imageUrl;
}
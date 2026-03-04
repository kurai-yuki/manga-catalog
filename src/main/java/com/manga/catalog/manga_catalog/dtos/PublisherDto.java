package com.manga.catalog.manga_catalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PublisherDto {
    private Integer id;

    private String name;

    private String description;

    private String imageUrl;
}

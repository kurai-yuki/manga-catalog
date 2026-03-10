package com.manga.catalog.manga_catalog.dtos.publisher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {
    private Integer id;

    private String name;

    private String description;

    private String imageUrl;
}

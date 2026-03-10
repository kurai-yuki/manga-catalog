package com.manga.catalog.manga_catalog.dtos.publisher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePublisherDto {
    private String name;

    private String description;

    private String imageUrl;
}
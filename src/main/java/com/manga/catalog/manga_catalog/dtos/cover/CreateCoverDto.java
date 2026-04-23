package com.manga.catalog.manga_catalog.dtos.cover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCoverDto {
    private Integer volumeId;

    private String url;
}

package com.manga.catalog.manga_catalog.dtos.manga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MangaCountDto {
    private int total;

    private int completed;

    private int ongoing;
}

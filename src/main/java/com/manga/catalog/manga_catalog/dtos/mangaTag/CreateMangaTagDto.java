package com.manga.catalog.manga_catalog.dtos.mangaTag;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaTagDto {
    private Integer mangaId;

    private List<Integer> tagIds;
}

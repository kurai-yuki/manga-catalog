package com.manga.catalog.manga_catalog.dtos.mangaTag;

import com.manga.catalog.manga_catalog.dtos.tag.TagDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MangaTagDto {
    private Integer id;

    private TagDto tag;
}

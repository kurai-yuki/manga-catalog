package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.manga.catalog.manga_catalog.dtos.mangaTag.CreateMangaTagDto;
import com.manga.catalog.manga_catalog.dtos.mangaTag.MangaTagDto;
import com.manga.catalog.manga_catalog.entities.MangaTag;

@Mapper(componentModel = "spring")
public interface MangaTagMapper {
    MangaTagDto toDto(MangaTag manga);

    List<MangaTagDto> toDto(List<MangaTag> mangaTags);

    MangaTag toEntity(MangaTagDto dto);

    MangaTag toEntity(CreateMangaTagDto dto);
}

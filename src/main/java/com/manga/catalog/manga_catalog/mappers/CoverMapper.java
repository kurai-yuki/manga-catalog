package com.manga.catalog.manga_catalog.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.cover.CreateCoverDto;
import com.manga.catalog.manga_catalog.entities.Cover;

@Mapper(componentModel = "spring")
public interface CoverMapper {
    CoverDto toDto(Cover cover);

    Cover toEntity(CoverDto dto);

    Cover toEntity(CreateCoverDto dto);

    List<CoverDto> toDtoList(List<Cover> mangas);

    /**
        Cover - Entity that will be update <br>
        Dto - Will update some datas from the entity
     **/
    default void update(Cover cover, CreateCoverDto dto) {
        cover.setMangaId(dto.getMangaId() != null ? dto.getMangaId() : cover.getMangaId());
        cover.setUrl(dto.getUrl() != null ? dto.getUrl() : cover.getUrl());
        cover.setVolumeNumber(dto.getVolumeNumber() != null ? dto.getVolumeNumber() : cover.getVolumeNumber());
    }
}

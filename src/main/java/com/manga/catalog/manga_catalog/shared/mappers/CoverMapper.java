package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.cover.CreateCoverDto;
import com.manga.catalog.manga_catalog.entities.Cover;
import com.manga.catalog.manga_catalog.entities.Volume;

@Mapper(componentModel = "spring")
public interface CoverMapper {
    @Mapping(source = "cover.volume.volumeNumber", target = "volumeNumber")
    CoverDto toDto(Cover cover);

    Cover toEntity(CoverDto dto);

    Cover toEntity(CreateCoverDto dto);

    List<CoverDto> toDtoList(List<Cover> covers);

    /**
     * Cover - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Cover cover, CreateCoverDto dto, Volume volume) {
        cover.setVolume(volume != null ? volume : cover.getVolume());
        cover.setUrl(dto.getUrl() != null ? dto.getUrl() : cover.getUrl());
    }
}

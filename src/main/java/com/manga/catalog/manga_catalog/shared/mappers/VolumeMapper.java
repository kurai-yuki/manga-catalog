package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.volume.CreateVolumeDto;
import com.manga.catalog.manga_catalog.dtos.volume.VolumeDto;
import com.manga.catalog.manga_catalog.entities.Volume;

@Mapper(componentModel = "spring")
public interface VolumeMapper {
    @Mapping(source = "volume.volumeNumber", target = "volumeNumber")
    @Mapping(source = "volume.id", target = "id")
    VolumeDto toDto(Volume volume, CoverDto cover);

    Volume toEntity(VolumeDto dto);

    Volume toEntity(CreateVolumeDto dto);

    List<VolumeDto> toDtoList(List<Volume> volumes);

    default List<VolumeDto> toDtoList(List<Volume> volumes, List<CoverDto> covers) {
        List<VolumeDto> dtoList = toDtoList(volumes);

        for (VolumeDto volume : dtoList) {
            CoverDto cover = covers.stream().filter((c) -> c.getVolumeNumber().equals(volume.getVolumeNumber()))
                    .findFirst().orElse(null);

            volume.setCover(cover);
        }

        return dtoList;
    };

    /**
     * Volume - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Volume volume, CreateVolumeDto dto) {
        volume.setLanguage(dto.getLanguage() != null ? dto.getLanguage() : volume.getLanguage());
        volume.setVolumeNumber(dto.getVolumeNumber() != null ? dto.getVolumeNumber() : volume.getVolumeNumber());
    }
}

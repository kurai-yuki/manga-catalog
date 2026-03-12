package com.manga.catalog.manga_catalog.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.manga.catalog.manga_catalog.dtos.mangaStaff.CreateMangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.MangaStaffDto;
import com.manga.catalog.manga_catalog.entities.MangaStaff;

@Mapper(componentModel = "spring")
public interface MangaStaffMapper {
    MangaStaffDto toDto(MangaStaff manga);

    List<MangaStaffDto> toDto(List<MangaStaff> manga);

    MangaStaff toEntity(MangaStaffDto dto);

    MangaStaff toEntity(CreateMangaStaffDto dto);
}

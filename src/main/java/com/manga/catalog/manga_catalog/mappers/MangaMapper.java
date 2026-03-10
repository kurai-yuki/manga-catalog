package com.manga.catalog.manga_catalog.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.manga.catalog.manga_catalog.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.dtos.manga.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.manga.MangaDto;
import com.manga.catalog.manga_catalog.entities.Manga;

@Mapper(componentModel = "spring")
public interface MangaMapper {
    MangaDto toDto(Manga manga);

    List<MangaDto> toDtoList(List<Manga> mangas);

    Manga toEntity(MangaDto dto);

    Manga toEntity(CreateMangaDto dto);

    /**
     * Convert Page<T> from spring into the custom Pagination<T> response
     **/
    default PaginationResponse<MangaDto> toPagination(Page<Manga> page) {
        PaginationResponse<MangaDto> response = new PaginationResponse<>();
        List<MangaDto> dto = toDtoList(page.getContent());

        response.setData(dto);
        response.setPagination(page.getPageable());

        return response;
    };

    /**
     * Manga - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Manga manga, CreateMangaDto dto) {
        manga.setTitle(dto.getTitle() != null ? dto.getTitle() : manga.getTitle());
        manga.setDescription(dto.getDescription() != null ? dto.getDescription() : manga.getDescription());
        manga.setStatus(dto.getStatus() != null ? dto.getStatus() : manga.getStatus());
        manga.setLaunchStatus(dto.getLaunchStatus() != null ? dto.getLaunchStatus() : manga.getLaunchStatus());
        manga.setImported(dto.getImported() != null ? dto.getImported() : manga.isImported());
        manga.setTotalVolumes(dto.getTotalVolumes() != null ? dto.getTotalVolumes() : manga.getTotalVolumes());
        manga.setReleaseDate(dto.getReleaseDate() != null ? dto.getReleaseDate() : manga.getReleaseDate());
    }
}

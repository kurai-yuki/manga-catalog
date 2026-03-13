package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.manga.catalog.manga_catalog.dtos.publisher.CreatePublisherDto;
import com.manga.catalog.manga_catalog.dtos.publisher.PublisherDto;
import com.manga.catalog.manga_catalog.entities.Publisher;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDto toDto(Publisher manga);

    List<PublisherDto> toDtoList(List<Publisher> mangas);

    Publisher toEntity(PublisherDto dto);

    Publisher toEntity(CreatePublisherDto dto);

    default PaginationResponse<PublisherDto> toPagination(Page<Publisher> page) {
        PaginationResponse<PublisherDto> response = new PaginationResponse<>();
        List<PublisherDto> dto = toDtoList(page.getContent());

        response.setData(dto);
        response.setPagination(page.getPageable());

        return response;
    };

    /**
     * Publisher - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Publisher manga, CreatePublisherDto dto) {
        manga.setName(dto.getName() != null ? dto.getName() : manga.getName());
        manga.setDescription(dto.getDescription() != null ? dto.getDescription() : manga.getDescription());
        manga.setImageUrl(dto.getImageUrl() != null ? dto.getImageUrl() : manga.getImageUrl());
    }
}

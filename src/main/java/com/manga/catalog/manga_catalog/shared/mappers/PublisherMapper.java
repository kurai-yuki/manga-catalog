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
    PublisherDto toDto(Publisher publisher);

    List<PublisherDto> toDto(List<Publisher> publishers);

    Publisher toEntity(PublisherDto dto);

    Publisher toEntity(CreatePublisherDto dto);

    default PaginationResponse<PublisherDto> toPagination(Page<Publisher> page) {
        PaginationResponse<PublisherDto> response = new PaginationResponse<>();
        List<PublisherDto> dto = toDto(page.getContent());

        response.setData(dto);
        response.setPagination(page.getPageable());

        return response;
    };

    /**
     * Publisher - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Publisher publisher, CreatePublisherDto dto) {
        publisher.setName(dto.getName() != null ? dto.getName() : publisher.getName());
        publisher.setDescription(dto.getDescription() != null ? dto.getDescription() : publisher.getDescription());
        publisher.setImageUrl(dto.getImageUrl() != null ? dto.getImageUrl() : publisher.getImageUrl());
    }
}

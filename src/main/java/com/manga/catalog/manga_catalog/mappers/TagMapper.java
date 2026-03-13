package com.manga.catalog.manga_catalog.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.manga.catalog.manga_catalog.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.entities.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag manga);

    List<TagDto> toDto(List<Tag> mangas);

    Tag toEntity(TagDto dto);

    Tag toEntity(CreateTagDto dto);

    /**
     * Convert Page<T> from spring into the custom Pagination<T> response
     **/
    default PaginationResponse<TagDto> toPagination(Page<Tag> page) {
        PaginationResponse<TagDto> response = new PaginationResponse<>();
        List<TagDto> dto = toDto(page.getContent());

        response.setData(dto);
        response.setPagination(page.getPageable());

        return response;
    };

    /**
     * Tag - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Tag manga, CreateTagDto dto) {
        manga.setName(dto.getName() != null ? dto.getName() : manga.getName());
        manga.setDescription(dto.getDescription() != null ? dto.getDescription() : manga.getDescription());
    }
}

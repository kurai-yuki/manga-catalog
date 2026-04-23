package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.entities.Tag;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);

    List<TagDto> toDto(List<Tag> tags);

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
    default void update(Tag tag, CreateTagDto dto) {
        tag.setName(dto.getName() != null ? dto.getName() : tag.getName());
        tag.setDescription(dto.getDescription() != null ? dto.getDescription() : tag.getDescription());
    }
}

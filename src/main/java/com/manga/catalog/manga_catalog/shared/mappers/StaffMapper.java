package com.manga.catalog.manga_catalog.shared.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.manga.catalog.manga_catalog.dtos.staff.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.staff.StaffDto;
import com.manga.catalog.manga_catalog.entities.Staff;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDto toDto(Staff staff);

    List<StaffDto> toDto(List<Staff> staff);

    Staff toEntity(StaffDto dto);

    Staff toEntity(CreateStaffDto dto);

    /**
     * Convert Page<T> from spring into the custom Pagination<T> response
     **/
    default PaginationResponse<StaffDto> toPagination(Page<Staff> page) {
        PaginationResponse<StaffDto> response = new PaginationResponse<>();
        List<StaffDto> dto = toDto(page.getContent());

        response.setData(dto);
        response.setPagination(page.getPageable());

        return response;
    };

    /**
     * Staff - Entity that will be update <br>
     * Dto - Will update some datas from the entity
     **/
    default void update(Staff staff, CreateStaffDto dto) {
        staff.setName(dto.getName() != null ? dto.getName() : staff.getName());
        staff.setDescription(dto.getDescription() != null ? dto.getDescription() : staff.getDescription());
    }
}

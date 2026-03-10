package com.manga.catalog.manga_catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.dtos.staff.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.staff.StaffDto;
import com.manga.catalog.manga_catalog.entities.Staff;
import com.manga.catalog.manga_catalog.impl.IService;
import com.manga.catalog.manga_catalog.mappers.StaffMapperImpl;
import com.manga.catalog.manga_catalog.repositories.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService implements IService<StaffDto, CreateStaffDto> {

    private final StaffRepository repository;
    private final StaffMapperImpl staffMapperImpl;

    @Override
    public PaginationResponse<StaffDto> findAll(PaginationRequest payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Staff> staffMembers = repository.findAll(pagination);

        PaginationResponse<StaffDto> dto = staffMapperImpl.toPagination(staffMembers);
        return dto;

    }

    @Override
    public StaffDto findById(int id) {
        Staff staffMember = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        StaffDto dto = staffMapperImpl.toDto(staffMember);
        return dto;
    }

    @Override
    public StaffDto add(CreateStaffDto payload) {
        Staff exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Staff staffMember = staffMapperImpl.toEntity(payload);

        Staff response = repository.save(staffMember);

        StaffDto dto = staffMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public StaffDto update(int id, CreateStaffDto payload) {
        Staff staff = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        staffMapperImpl.update(staff, payload);

        Staff response = repository.save(staff);

        StaffDto dto = staffMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

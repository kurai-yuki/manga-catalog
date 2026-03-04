package com.manga.catalog.manga_catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.dtos.StaffDto;
import com.manga.catalog.manga_catalog.entities.Staff;
import com.manga.catalog.manga_catalog.repositories.StaffRepository;

@Service
public class StaffService {
    @Autowired
    StaffRepository repository;

    public Page<StaffDto> findAll(Pagination payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Staff> staffMembers = repository.findAll(pagination);

        Page<StaffDto> dto = staffMembers.map((staff) -> Staff.toDto(staff));
        return dto;
    }

    public StaffDto findById(int id) {
        Staff staffMember = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        StaffDto StaffDto = Staff.toDto(staffMember);

        return StaffDto;
    }

    public StaffDto add(CreateStaffDto payload) {
        Staff exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Staff staffMember = new Staff(payload);
        Staff response = repository.save(staffMember);

        StaffDto dto = Staff.toDto(response);
        return dto;
    }

    public StaffDto update(int id, CreateStaffDto payload) {
        boolean exists = repository.existsById(id);
        if (exists) {
            throw new Error("exists");
        }

        Staff updatedStaff = new Staff(id, payload);
        Staff response = repository.save(updatedStaff);

        StaffDto dto = Staff.toDto(response);
        return dto;
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

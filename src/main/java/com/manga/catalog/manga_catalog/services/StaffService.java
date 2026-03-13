package com.manga.catalog.manga_catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.staff.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.staff.StaffDto;
import com.manga.catalog.manga_catalog.entities.Staff;
import com.manga.catalog.manga_catalog.repositories.StaffRepository;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.shared.exceptions.ErrorMessages;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;
import com.manga.catalog.manga_catalog.shared.mappers.StaffMapperImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository repository;
    private final StaffMapperImpl staffMapperImpl;

    public PaginationResponse<StaffDto> findAll(PaginationRequest payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Staff> staffMembers = repository.findAll(pagination);

        return staffMapperImpl.toPagination(staffMembers);

    }

    public StaffDto findById(int id) {
        Staff staffMember = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundStaff(id));
                });

        return staffMapperImpl.toDto(staffMember);
    }

    public StaffDto add(CreateStaffDto payload) {
        Staff exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new AlredyExistsException(ErrorMessages.staffAlredyExists());
        }

        Staff staffMember = staffMapperImpl.toEntity(payload);

        Staff response = repository.save(staffMember);

        return staffMapperImpl.toDto(response);
    }

    public StaffDto update(int id, CreateStaffDto payload) {
        Staff staff = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundStaff(id));
                });

        staffMapperImpl.update(staff, payload);

        Staff response = repository.save(staff);

        return staffMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new NotFoundException(ErrorMessages.notFoundStaff(id));
        }

        repository.deleteById(id);
    }
}

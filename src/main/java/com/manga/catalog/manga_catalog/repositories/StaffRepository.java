package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByName(String name);
}

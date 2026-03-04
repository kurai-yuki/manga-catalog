package com.manga.catalog.manga_catalog.entities;

import java.time.LocalDateTime;

import com.manga.catalog.manga_catalog.dtos.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.StaffDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    // Create
    public Staff(CreateStaffDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }

    // Update
    public Staff(int id, CreateStaffDto dto) {
        this.id = id;
        this.name = dto.getName();
        this.description = dto.getDescription();
    }

    public static StaffDto toDto(Staff manga) {
        return new StaffDto(
                manga.getId(),
                manga.getName(),
                manga.getDescription());
    }
}

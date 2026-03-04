package com.manga.catalog.manga_catalog.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.manga.catalog.manga_catalog.dtos.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.MangaDto;
import com.manga.catalog.manga_catalog.enums.LaunchStatusEnum;
import com.manga.catalog.manga_catalog.enums.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private LaunchStatusEnum launchStatus;

    private boolean imported;

    private Integer totalVolumes;

    private Integer publisherId;

    private LocalDateTime releaseDate;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    // Create
    public Manga(CreateMangaDto dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
        this.launchStatus = dto.getLaunchStatus();
        this.imported = dto.isImported();
        this.totalVolumes = dto.getTotalVolumes();
        this.publisherId = dto.getPublisherId();
        this.releaseDate = dto.getReleaseDate();
    }

    // Update
    public Manga(int id, CreateMangaDto dto) {
        this.id = id;
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
        this.launchStatus = dto.getLaunchStatus();
        this.imported = dto.isImported();
        this.totalVolumes = dto.getTotalVolumes();
        this.publisherId = dto.getPublisherId();
        this.releaseDate = dto.getReleaseDate();
    }

    public static MangaDto toDto(Manga manga) {
        return new MangaDto(
                manga.getId(),
                manga.getTitle(),
                manga.getDescription(),
                manga.getStatus(),
                manga.getLaunchStatus(),
                manga.isImported(),
                manga.getTotalVolumes(),
                manga.getPublisherId(),
                manga.getReleaseDate());
    }
}

package com.manga.catalog.manga_catalog.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.manga.catalog.manga_catalog.dtos.CoverDto;
import com.manga.catalog.manga_catalog.dtos.CreateCoverDto;

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
public class Cover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mangaId;

    private String url;

    private int volumeNumber;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    // Create
    public Cover(CreateCoverDto dto) {
        this.mangaId = dto.getMangaId();
        this.url = dto.getUrl();
        this.volumeNumber = dto.getVolumeNumber();
    }

    // Update
    public Cover(int id, CreateCoverDto dto) {
        this.id = id;
        this.mangaId = dto.getMangaId();
        this.url = dto.getUrl();
        this.volumeNumber = dto.getVolumeNumber();
    }

    public static CoverDto toDto(Cover cover) {
        return new CoverDto(
                cover.getId(),
                cover.getUrl(),
                cover.getVolumeNumber());
    }

    public static List<CoverDto> toDto(List<Cover> covers) {
        return covers.stream().map((cover) -> {
            return new CoverDto(
                    cover.getId(),
                    cover.getUrl(),
                    cover.getVolumeNumber());
        }).toList();
    }
}

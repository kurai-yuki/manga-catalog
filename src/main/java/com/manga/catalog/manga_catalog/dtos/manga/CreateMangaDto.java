package com.manga.catalog.manga_catalog.dtos.manga;

import java.time.LocalDateTime;

import com.manga.catalog.manga_catalog.enums.LaunchStatusEnum;
import com.manga.catalog.manga_catalog.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaDto {
    private String title;

    private String description;

    private StatusEnum status;

    private LaunchStatusEnum launchStatus;

    private Boolean imported;

    private Integer totalVolumes;

    private Integer publisherId;

    private LocalDateTime releaseDate;
}
package com.manga.catalog.manga_catalog.dtos;

import java.time.LocalDateTime;

import com.manga.catalog.manga_catalog.enums.LaunchStatusEnum;
import com.manga.catalog.manga_catalog.enums.StatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMangaDto {
    private String title;

    private String description;

    private StatusEnum status;

    private LaunchStatusEnum launchStatus;

    private boolean imported;

    private Integer totalVolumes;

    private Integer publisherId;

    private LocalDateTime releaseDate;
}
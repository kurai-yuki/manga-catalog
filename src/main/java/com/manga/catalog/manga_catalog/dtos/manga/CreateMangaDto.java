package com.manga.catalog.manga_catalog.dtos.manga;

import java.time.LocalDateTime;

import com.manga.catalog.manga_catalog.shared.enums.LaunchStatusEnum;
import com.manga.catalog.manga_catalog.shared.enums.StatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaDto {
    @NotBlank
    private String title;
    
    private String description;
    
    @NotNull
    private StatusEnum status;
    
    @NotNull
    private LaunchStatusEnum launchStatus;

    private Boolean imported;
    
    private Integer totalVolumes;
    
    @NotNull
    private Integer publisherId;
    
    private LocalDateTime releaseDate;
}
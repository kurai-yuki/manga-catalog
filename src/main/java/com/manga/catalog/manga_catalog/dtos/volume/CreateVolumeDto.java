package com.manga.catalog.manga_catalog.dtos.volume;

import com.manga.catalog.manga_catalog.shared.enums.MangaLanguageEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateVolumeDto {
    @NotBlank
    private String coverUrl;

    @NotNull
    private Integer mangaId;

    @NotNull
    private MangaLanguageEnum language;

    @NotNull
    private Integer volumeNumber;
}

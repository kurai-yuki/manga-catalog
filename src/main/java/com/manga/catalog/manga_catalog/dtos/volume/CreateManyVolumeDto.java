package com.manga.catalog.manga_catalog.dtos.volume;

import com.manga.catalog.manga_catalog.shared.enums.MangaLanguageEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateManyVolumeDto {
    private Integer mangaId;

    private MangaLanguageEnum language;

    private Integer volumeNumber;
}

package com.manga.catalog.manga_catalog.dtos.volume;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.shared.enums.MangaLanguageEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolumeDto {
    private int id;

    private CoverDto cover;

    private MangaLanguageEnum language;

    private int volumeNumber;
}

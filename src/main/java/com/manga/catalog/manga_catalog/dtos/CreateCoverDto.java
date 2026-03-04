package com.manga.catalog.manga_catalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCoverDto {
    private Integer mangaId;

    private String url;

    private int volumeNumber;
}

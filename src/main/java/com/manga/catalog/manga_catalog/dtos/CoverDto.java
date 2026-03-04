package com.manga.catalog.manga_catalog.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoverDto {
    private Integer id;

    private String url;

    private int volumeNumber;
}

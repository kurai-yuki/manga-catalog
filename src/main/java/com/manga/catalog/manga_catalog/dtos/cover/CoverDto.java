package com.manga.catalog.manga_catalog.dtos.cover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverDto {
    private Integer id;

    private String url;
    
    private Integer volumeNumber;
}

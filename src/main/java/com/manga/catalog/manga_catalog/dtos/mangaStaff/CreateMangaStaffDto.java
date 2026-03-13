package com.manga.catalog.manga_catalog.dtos.mangaStaff;

import com.manga.catalog.manga_catalog.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaStaffDto {
    private RoleEnum role;

    private Integer mangaId;

    private Integer staffId;
}

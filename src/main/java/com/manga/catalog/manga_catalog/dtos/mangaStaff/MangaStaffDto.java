package com.manga.catalog.manga_catalog.dtos.mangaStaff;

import com.manga.catalog.manga_catalog.dtos.manga.MangaDto;
import com.manga.catalog.manga_catalog.dtos.staff.StaffDto;
import com.manga.catalog.manga_catalog.enums.StaffRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MangaStaffDto {
    private Integer id;

    private StaffRoleEnum role;

    private MangaDto manga;

    private StaffDto staff;
}

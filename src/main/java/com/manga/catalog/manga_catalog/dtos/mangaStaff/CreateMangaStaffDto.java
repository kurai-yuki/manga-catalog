package com.manga.catalog.manga_catalog.dtos.mangaStaff;

import com.manga.catalog.manga_catalog.shared.enums.RoleEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaStaffDto {

    @NotNull
    private RoleEnum role;

    @NotNull
    private Integer mangaId;

    @NotNull
    private Integer staffId;
}

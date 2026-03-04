package com.manga.catalog.manga_catalog.dtos;

import java.util.List;

import com.manga.catalog.manga_catalog.enums.StaffRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StaffDto {
    private Integer id;

    private String name;

    private String description;

    // private List<StaffRoleEnum> roles;
}

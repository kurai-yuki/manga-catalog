package com.manga.catalog.manga_catalog.dtos.staff;

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
}

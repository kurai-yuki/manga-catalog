package com.manga.catalog.manga_catalog.dtos.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffDto {
    private String name;

    private String description;
}

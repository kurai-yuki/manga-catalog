package com.manga.catalog.manga_catalog.dtos.staff;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

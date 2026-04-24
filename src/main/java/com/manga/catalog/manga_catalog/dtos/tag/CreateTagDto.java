package com.manga.catalog.manga_catalog.dtos.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTagDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

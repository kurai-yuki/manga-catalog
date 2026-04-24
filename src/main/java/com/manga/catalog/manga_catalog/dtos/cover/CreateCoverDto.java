package com.manga.catalog.manga_catalog.dtos.cover;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCoverDto {
    @NotNull
    private Integer volumeId;

    @NotBlank
    private String url;
}

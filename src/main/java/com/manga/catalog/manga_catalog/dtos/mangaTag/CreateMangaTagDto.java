package com.manga.catalog.manga_catalog.dtos.mangaTag;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMangaTagDto {
    @NotNull
    private Integer mangaId;

    @NotNull
    private List<Integer> tagIds;
}

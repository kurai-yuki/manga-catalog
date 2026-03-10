package com.manga.catalog.manga_catalog.dtos.manga;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.manga.catalog.manga_catalog.dtos.publisher.PublisherDto;
import com.manga.catalog.manga_catalog.enums.LaunchStatusEnum;
import com.manga.catalog.manga_catalog.enums.StatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MangaDto {
    private Integer id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private LaunchStatusEnum launchStatus;

    private boolean imported;

    private Integer totalVolumes;

    private PublisherDto publisher;

    private LocalDateTime releaseDate;
}

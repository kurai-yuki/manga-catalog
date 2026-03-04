package com.manga.catalog.manga_catalog.entities;

import java.time.LocalDateTime;

import com.manga.catalog.manga_catalog.dtos.CreatePublisherDto;
import com.manga.catalog.manga_catalog.dtos.PublisherDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String imageUrl;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public Publisher(CreatePublisherDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.imageUrl = dto.getImageUrl();
    }

    public Publisher(int id, CreatePublisherDto dto) {
        this.id = id;
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.imageUrl = dto.getImageUrl();
    }

    public static PublisherDto toDto(Publisher publisher) {
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getDescription(),
                publisher.getImageUrl());
    }
}

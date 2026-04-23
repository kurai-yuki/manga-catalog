package com.manga.catalog.manga_catalog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cover extends BaseEntity {
    private String url;

    @ManyToOne
    @JoinColumn(name = "volume_id")
    private Volume volume;
}

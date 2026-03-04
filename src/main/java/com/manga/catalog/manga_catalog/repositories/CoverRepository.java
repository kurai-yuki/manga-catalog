package com.manga.catalog.manga_catalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.Cover;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
    List<Cover> findByMangaId(Integer mangaId);

    boolean existsByMangaIdAndVolumeNumber(Integer mangaId, int volumeNumber);
}

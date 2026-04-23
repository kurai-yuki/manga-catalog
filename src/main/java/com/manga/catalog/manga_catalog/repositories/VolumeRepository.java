package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.Volume;
import java.util.List;


public interface VolumeRepository extends JpaRepository<Volume, Integer> {
    List<Volume> findByMangaId(Integer mangaId);

    boolean existsByVolumeNumber(int volumeNumber);
}

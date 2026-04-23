package com.manga.catalog.manga_catalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manga.catalog.manga_catalog.entities.Cover;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
    Cover findByVolumeId(Integer volumeId);

    boolean existsByVolumeId(Integer volumeId);

    @Query("SELECT c FROM Cover c WHERE c.volume.manga.id = ?1")
    List<Cover> findByMangaId(Integer mangaId);
}

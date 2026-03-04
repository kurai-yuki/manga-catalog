package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.enums.StatusEnum;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
    Manga findByTitle(String title);

    @Query("SELECT COUNT(*) FROM Manga m WHERE m.status = ?1")
    int countByStatus(StatusEnum status);
}

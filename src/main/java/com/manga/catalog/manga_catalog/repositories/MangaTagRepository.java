package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manga.catalog.manga_catalog.entities.MangaTag;
import java.util.List;

public interface MangaTagRepository extends JpaRepository<MangaTag, Integer> {
    List<MangaTag> findAllByMangaId(Integer mangaId);

    @Query("SELECT m FROM MangaTag m WHERE m.tag.id IN (?2) AND m.manga.id = ?1")
    List<MangaTag> findByMangaIdAndTagIds(Integer mangaId, List<Integer> tagIds);
}

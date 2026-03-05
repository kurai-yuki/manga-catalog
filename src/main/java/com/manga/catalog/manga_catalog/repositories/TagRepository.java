package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}

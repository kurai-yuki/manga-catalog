package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    Publisher findByName(String name);
}

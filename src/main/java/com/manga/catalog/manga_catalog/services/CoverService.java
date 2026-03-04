package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.CoverDto;
import com.manga.catalog.manga_catalog.dtos.CreateCoverDto;
import com.manga.catalog.manga_catalog.entities.Cover;
import com.manga.catalog.manga_catalog.repositories.CoverRepository;

@Service
public class CoverService {
    @Autowired
    CoverRepository repository;

    public List<CoverDto> findCoversByMangaId(int mangaId) {
        List<Cover> covers = repository.findByMangaId(mangaId);
        List<CoverDto> CoversDto = Cover.toDto(covers);

        return CoversDto;
    }

    public CoverDto findById(int id) {
        Cover cover = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        CoverDto CoverDto = Cover.toDto(cover);

        return CoverDto;
    }

    public CoverDto add(CreateCoverDto payload) {
        boolean exists = repository.existsByMangaIdAndVolumeNumber(
                payload.getMangaId(),
                payload.getVolumeNumber());

        if (exists) {
            throw new Error("exists");
        }

        Cover cover = new Cover(payload);
        Cover response = repository.save(cover);

        CoverDto dto = Cover.toDto(response);
        return dto;
    }

    public CoverDto update(int id, CreateCoverDto payload) {
        boolean exists = repository.existsById(id);
        if (exists) {
            throw new Error("exists");
        }

        Cover updatedCover = new Cover(id, payload);
        Cover response = repository.save(updatedCover);

        CoverDto dto = Cover.toDto(response);
        return dto;
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

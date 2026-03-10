package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.cover.CreateCoverDto;
import com.manga.catalog.manga_catalog.entities.Cover;
import com.manga.catalog.manga_catalog.impl.IService;
import com.manga.catalog.manga_catalog.mappers.CoverMapperImpl;
import com.manga.catalog.manga_catalog.repositories.CoverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverService implements IService<CoverDto, CreateCoverDto> {

    private final CoverMapperImpl coverMapperImpl;
    private final CoverRepository repository;

    public List<CoverDto> findCoversByMangaId(int mangaId) {
        List<Cover> covers = repository.findByMangaId(mangaId);
        List<CoverDto> coversDto = coverMapperImpl.toDtoList(covers);

        return coversDto;
    }

    @Override
    public CoverDto findById(int id) {
        Cover cover = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exits");
                });

        CoverDto CoverDto = coverMapperImpl.toDto(cover);
        return CoverDto;
    }

    @Override
    public CoverDto add(CreateCoverDto payload) {
        boolean exists = repository.existsByMangaIdAndVolumeNumber(
                payload.getMangaId(),
                payload.getVolumeNumber());

        if (exists) {
            throw new Error("exists");
        }

        Cover cover = coverMapperImpl.toEntity(payload);
        Cover response = repository.save(cover);

        CoverDto dto = coverMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public CoverDto update(int id, CreateCoverDto payload) {
        Cover cover = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        coverMapperImpl.update(cover, payload);

        Cover response = repository.save(cover);

        CoverDto dto = coverMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

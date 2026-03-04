package com.manga.catalog.manga_catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.MangaCountDto;
import com.manga.catalog.manga_catalog.dtos.MangaDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.enums.StatusEnum;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;

@Service
public class MangaService {

    @Autowired
    MangaRepository repository;

    public MangaCountDto mangaCount() {
        int allMangas = (int) repository.count();
        int allCompletedMangas = repository.countByStatus(StatusEnum.COMPLETE);
        int allOngoingMangas = repository.countByStatus(StatusEnum.ONGOING);

        return new MangaCountDto(allMangas, allCompletedMangas, allOngoingMangas);
    }

    public Page<MangaDto> findAll(Pagination payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Manga> mangas = repository.findAll(pagination);

        Page<MangaDto> dto = mangas.map((manga) -> Manga.toDto(manga));
        return dto;
    }

    public MangaDto findById(int id) {
        Manga manga = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        MangaDto mangaDto = Manga.toDto(manga);

        return mangaDto;
    }

    public MangaDto add(CreateMangaDto payload) {
        Manga exists = repository.findByTitle(payload.getTitle());

        if (exists != null) {
            throw new Error("exists");
        }

        Manga manga = new Manga(payload);
        Manga response = repository.save(manga);

        MangaDto dto = Manga.toDto(response);
        return dto;
    }

    public MangaDto update(int id, CreateMangaDto payload) {
        boolean exists = repository.existsById(id);
        if (exists) {
            throw new Error("exists");
        }

        Manga updatedManga = new Manga(id, payload);
        Manga response = repository.save(updatedManga);

        MangaDto dto = Manga.toDto(response);
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

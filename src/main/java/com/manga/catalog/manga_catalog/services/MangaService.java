package com.manga.catalog.manga_catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.dtos.manga.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.manga.MangaCountDto;
import com.manga.catalog.manga_catalog.dtos.manga.MangaDto;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.entities.Publisher;
import com.manga.catalog.manga_catalog.enums.StatusEnum;
import com.manga.catalog.manga_catalog.impl.IService;
import com.manga.catalog.manga_catalog.mappers.MangaMapperImpl;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MangaService implements IService<MangaDto, CreateMangaDto> {

    private final MangaRepository mangaRepository;
    private final PublisherRepository publisherRepository;
    private final MangaMapperImpl mangaMapperImpl;

    public MangaCountDto mangaCount() {
        int allMangas = (int) mangaRepository.count();
        int allCompletedMangas = mangaRepository.countByStatus(StatusEnum.COMPLETE);
        int allOngoingMangas = mangaRepository.countByStatus(StatusEnum.ONGOING);

        return new MangaCountDto(allMangas, allCompletedMangas, allOngoingMangas);
    }

    @Override
    public PaginationResponse<MangaDto> findAll(PaginationRequest payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Manga> mangas = mangaRepository.findAll(pagination);

        PaginationResponse<MangaDto> dto = mangaMapperImpl.toPagination(mangas);
        return dto;
    }

    @Override
    public MangaDto findById(int id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        MangaDto mangaDto = mangaMapperImpl.toDto(manga);
        return mangaDto;
    }

    @Override
    public MangaDto add(CreateMangaDto payload) {
        Manga exists = mangaRepository.findByTitle(payload.getTitle());

        if (exists != null) {
            throw new Error("exists");
        }

        Publisher publisher = publisherRepository.findById(payload.getPublisherId())
                .orElseThrow(() -> {
                    throw new Error("exists 2");
                });

        Manga manga = mangaMapperImpl.toEntity(payload);
        manga.setPublisher(publisher);

        Manga response = mangaRepository.save(manga);

        MangaDto mangaDto = mangaMapperImpl.toDto(response);
        return mangaDto;
    }

    @Override
    public MangaDto update(int id, CreateMangaDto payload) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        Publisher publisher = publisherRepository.findById(payload.getPublisherId())
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        mangaMapperImpl.update(manga, payload);
        manga.setPublisher(publisher);

        Manga response = mangaRepository.save(manga);

        MangaDto dto = mangaMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public void remove(int id) {
        boolean exists = mangaRepository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        mangaRepository.deleteById(id);
    }
}

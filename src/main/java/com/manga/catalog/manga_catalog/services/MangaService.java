package com.manga.catalog.manga_catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.manga.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.manga.MangaCountDto;
import com.manga.catalog.manga_catalog.dtos.manga.MangaDto;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.entities.Publisher;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.PublisherRepository;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.shared.enums.StatusEnum;
import com.manga.catalog.manga_catalog.shared.exceptions.ErrorMessages;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;
import com.manga.catalog.manga_catalog.shared.mappers.MangaMapperImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MangaService {

    private final MangaRepository mangaRepository;
    private final PublisherRepository publisherRepository;
    private final MangaMapperImpl mangaMapperImpl;

    public MangaCountDto mangaCount() {
        int allMangas = (int) mangaRepository.count();
        int allCompletedMangas = mangaRepository.countByStatus(StatusEnum.COMPLETE);
        int allOngoingMangas = mangaRepository.countByStatus(StatusEnum.ONGOING);

        return new MangaCountDto(allMangas, allCompletedMangas, allOngoingMangas);
    }

    public PaginationResponse<MangaDto> findAll(PaginationRequest payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Manga> mangas = mangaRepository.findAll(pagination);

        return mangaMapperImpl.toPagination(mangas);
    }

    public MangaDto findById(int id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundManga(id));
                });

        MangaDto mangaDto = mangaMapperImpl.toDto(manga);
        return mangaDto;
    }

    public MangaDto add(CreateMangaDto payload) {
        Manga exists = mangaRepository.findByTitle(payload.getTitle());

        if (exists != null) {
            throw new AlredyExistsException(ErrorMessages.mangaAlredyExists());
        }

        Publisher publisher = publisherRepository.findById(payload.getPublisherId())
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundPublisher(payload.getPublisherId()));
                });

        Manga manga = mangaMapperImpl.toEntity(payload);
        manga.setPublisher(publisher);

        Manga response = mangaRepository.save(manga);

        return mangaMapperImpl.toDto(response);
    }

    public MangaDto update(int id, CreateMangaDto payload) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundManga(id));
                });

        Publisher publisher = publisherRepository.findById(payload.getPublisherId())
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundPublisher(payload.getPublisherId()));
                });

        mangaMapperImpl.update(manga, payload);
        manga.setPublisher(publisher);

        Manga response = mangaRepository.save(manga);

        return mangaMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = mangaRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException(ErrorMessages.notFoundManga(id));
        }

        mangaRepository.deleteById(id);
    }
}

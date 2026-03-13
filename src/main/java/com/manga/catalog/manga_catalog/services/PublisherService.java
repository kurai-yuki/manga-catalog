package com.manga.catalog.manga_catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.dtos.PaginationResponse;
import com.manga.catalog.manga_catalog.dtos.publisher.CreatePublisherDto;
import com.manga.catalog.manga_catalog.dtos.publisher.PublisherDto;
import com.manga.catalog.manga_catalog.entities.Publisher;
import com.manga.catalog.manga_catalog.mappers.PublisherMapperImpl;
import com.manga.catalog.manga_catalog.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherMapperImpl publisherMapperImpl;
    private final PublisherRepository repository;

    public PaginationResponse<PublisherDto> findAll(PaginationRequest payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Publisher> publishers = repository.findAll(pagination);

        return publisherMapperImpl.toPagination(publishers);
    }

    public PublisherDto findById(int id) {
        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        return publisherMapperImpl.toDto(publisher);
    }

    public PublisherDto add(CreatePublisherDto payload) {
        Publisher exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Publisher publisher = publisherMapperImpl.toEntity(payload);
        Publisher response = repository.save(publisher);

        return publisherMapperImpl.toDto(response);
    }

    public PublisherDto update(int id, CreatePublisherDto payload) {
        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        publisherMapperImpl.update(publisher, payload);

        Publisher response = repository.save(publisher);

        return publisherMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

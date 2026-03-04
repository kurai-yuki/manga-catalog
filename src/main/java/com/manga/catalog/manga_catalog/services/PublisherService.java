package com.manga.catalog.manga_catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.CreatePublisherDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.dtos.PublisherDto;
import com.manga.catalog.manga_catalog.entities.Publisher;
import com.manga.catalog.manga_catalog.repositories.PublisherRepository;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository repository;

    public Page<PublisherDto> findAll(Pagination payload) {
        Sort sort = Sort.by(payload.getOrderDirection(), payload.getOrderBy());
        Pageable pagination = PageRequest.of(payload.getPage(), payload.getPageSize(), sort);

        Page<Publisher> publishers = repository.findAll(pagination);

        Page<PublisherDto> dto = publishers.map((publisher) -> Publisher.toDto(publisher));
        return dto;
    }

    public PublisherDto findById(int id) {
        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        PublisherDto publisherDto = Publisher.toDto(publisher);

        return publisherDto;
    }

    public PublisherDto add(CreatePublisherDto payload) {
        Publisher exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Publisher publisher = new Publisher(payload);
        Publisher response = repository.save(publisher);

        PublisherDto dto = Publisher.toDto(response);
        return dto;
    }

    public PublisherDto update(int id, CreatePublisherDto payload) {
        boolean exists = repository.existsById(id);
        if (exists) {
            throw new Error("exists");
        }

        Publisher updatedPublisher = new Publisher(id, payload);
        Publisher response = repository.save(updatedPublisher);

        PublisherDto dto = Publisher.toDto(response);
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

package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.entities.Tag;
import com.manga.catalog.manga_catalog.mappers.TagMapperImpl;
import com.manga.catalog.manga_catalog.repositories.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;
    private final TagMapperImpl tagMapperImpl;

    public List<TagDto> findAll() {
        List<Tag> tags = repository.findAll();

        return tagMapperImpl.toDto(tags);
    }

    public TagDto findById(int id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        return tagMapperImpl.toDto(tag);
    }

    public TagDto add(CreateTagDto payload) {
        Tag exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Tag tag = tagMapperImpl.toEntity(payload);

        Tag response = repository.save(tag);

        return tagMapperImpl.toDto(response);
    }

    public TagDto update(int id, CreateTagDto payload) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        tagMapperImpl.update(tag, payload);

        Tag response = repository.save(tag);

        return tagMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        repository.deleteById(id);
    }
}

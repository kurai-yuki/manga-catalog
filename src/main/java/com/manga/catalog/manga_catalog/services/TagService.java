package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.TagDto;
import com.manga.catalog.manga_catalog.entities.Tag;
import com.manga.catalog.manga_catalog.repositories.TagRepository;

@Service
public class TagService {
    @Autowired
    TagRepository repository;

    public List<TagDto> findAll() {
        List<Tag> tags = repository.findAll();
        List<TagDto> dto = tags.stream()
                .map((tag) -> Tag.toDto(tag))
                .toList();
        return dto;
    }

    public TagDto findById(int id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        TagDto TagDto = Tag.toDto(tag);

        return TagDto;
    }

    public TagDto add(CreateTagDto payload) {
        Tag exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Tag tag = new Tag(payload);
        Tag response = repository.save(tag);

        TagDto dto = Tag.toDto(response);
        return dto;
    }

    public TagDto update(int id, CreateTagDto payload) {
        boolean exists = repository.existsById(id);
        if (exists) {
            throw new Error("exists");
        }

        Tag updatedTag = new Tag(id, payload);
        Tag response = repository.save(updatedTag);

        TagDto dto = Tag.toDto(response);
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

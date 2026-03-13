package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.entities.Tag;
import com.manga.catalog.manga_catalog.impl.IService;
import com.manga.catalog.manga_catalog.mappers.TagMapperImpl;
import com.manga.catalog.manga_catalog.repositories.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService implements IService<TagDto, CreateTagDto> {

    private final TagRepository repository;
    private final TagMapperImpl tagMapperImpl;

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = repository.findAll();

        List<TagDto> dto = tagMapperImpl.toDto(tags);
        return dto;
    }

    @Override
    public TagDto findById(int id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("teste");
                });

        TagDto dto = tagMapperImpl.toDto(tag);
        return dto;
    }

    @Override
    public TagDto add(CreateTagDto payload) {
        Tag exists = repository.findByName(payload.getName());

        if (exists != null) {
            throw new Error("exists");
        }

        Tag tag = tagMapperImpl.toEntity(payload);

        Tag response = repository.save(tag);

        TagDto dto = tagMapperImpl.toDto(response);
        return dto;
    }

    @Override
    public TagDto update(int id, CreateTagDto payload) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });

        tagMapperImpl.update(tag, payload);

        Tag response = repository.save(tag);

        TagDto dto = tagMapperImpl.toDto(response);
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

package com.manga.catalog.manga_catalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.mangaTag.CreateMangaTagDto;
import com.manga.catalog.manga_catalog.dtos.mangaTag.MangaTagDto;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.entities.MangaTag;
import com.manga.catalog.manga_catalog.entities.Tag;
import com.manga.catalog.manga_catalog.mappers.MangaTagMapperImpl;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.MangaTagRepository;
import com.manga.catalog.manga_catalog.repositories.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MangaTagService {

    private final MangaTagRepository mangaTagRepository;
    private final MangaRepository mangaRepository;
    private final TagRepository tagRepository;
    private final MangaTagMapperImpl mangaTagMapperImpl;

    public List<MangaTagDto> findMangaTags(int mangaId) {
        List<MangaTag> mangaTags = mangaTagRepository.findAllByMangaId(mangaId);

        return mangaTagMapperImpl.toDto(mangaTags);
    }

    public MangaTagDto findById(int mangaTagId) {
        MangaTag tag = mangaTagRepository.findById(mangaTagId)
                .orElseThrow(() -> {
                    throw new Error("exists1");
                });

        return mangaTagMapperImpl.toDto(tag);
    }

    public List<MangaTagDto> addMangaTags(CreateMangaTagDto payload) {
        List<MangaTag> exists = mangaTagRepository.findByMangaIdAndTagIds(payload.getMangaId(), payload.getTagIds());
        if (!exists.isEmpty()) {
            throw new Error("exists alredy");
        }

        Manga manga = mangaRepository.findById(payload.getMangaId())
                .orElseThrow(() -> {
                    throw new Error("not exists1");
                });

        List<Tag> tags = tagRepository.findAllById(payload.getTagIds());
        checkIfAllTagsExists(tags, payload.getTagIds());

        List<MangaTag> mangaTags = tags
                .stream()
                .map((tag) -> new MangaTag(manga, tag))
                .toList();

        List<MangaTag> response = mangaTagRepository.saveAll(mangaTags);
        return mangaTagMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = mangaTagRepository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        try {
            mangaTagRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error("Delete failed");
        }
    }

    private void checkIfAllTagsExists(List<Tag> tags, List<Integer> allTagIds) {
        if (tags.isEmpty()) {
            throw new Error("not exists");
        }

        if (tags.size() < allTagIds.size()) {
            Set<Integer> foundedIds = tags
                    .stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());

            List<Integer> failedIds = new ArrayList<Integer>();

            for (Integer id : allTagIds) {
                boolean idExists = foundedIds.contains(id);

                if (!idExists) {
                    failedIds.add(id);
                }
            }

            if (!failedIds.isEmpty()) {
                throw new Error("couldnt find some tags");
            }
        }
    }

}

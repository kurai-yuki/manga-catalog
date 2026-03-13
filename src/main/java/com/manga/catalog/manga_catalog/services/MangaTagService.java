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
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.MangaTagRepository;
import com.manga.catalog.manga_catalog.repositories.TagRepository;
import com.manga.catalog.manga_catalog.shared.exceptions.ErrorMessages;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;
import com.manga.catalog.manga_catalog.shared.mappers.MangaTagMapperImpl;

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

    public MangaTagDto findById(int id) {
        MangaTag tag = mangaTagRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundMangaTag(id));
                });

        return mangaTagMapperImpl.toDto(tag);
    }

    public List<MangaTagDto> addMangaTags(CreateMangaTagDto payload) {
        List<MangaTag> exists = mangaTagRepository.findByMangaIdAndTagIds(payload.getMangaId(), payload.getTagIds());
        if (!exists.isEmpty()) {
            throw new AlredyExistsException(ErrorMessages.mangaTagAlredyExists());
        }

        Manga manga = mangaRepository.findById(payload.getMangaId())
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundManga(payload.getMangaId()));
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
            throw new NotFoundException(ErrorMessages.notFoundMangaTag(id));
        }

        mangaTagRepository.deleteById(id);
    }

    private void checkIfAllTagsExists(List<Tag> tags, List<Integer> allTagIds) {
        if (tags.isEmpty()) {
            throw new NotFoundException(ErrorMessages.notFoundAnyMangaTag());
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
                throw new NotFoundException(ErrorMessages.notFoundSomeMangaTag());
            }
        }
    }

}

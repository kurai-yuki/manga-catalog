package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.cover.CreateCoverDto;
import com.manga.catalog.manga_catalog.entities.Cover;
import com.manga.catalog.manga_catalog.entities.Volume;
import com.manga.catalog.manga_catalog.repositories.CoverRepository;
import com.manga.catalog.manga_catalog.repositories.VolumeRepository;
import com.manga.catalog.manga_catalog.shared.exceptions.ErrorMessages;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;
import com.manga.catalog.manga_catalog.shared.mappers.CoverMapperImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverMapperImpl coverMapperImpl;
    private final CoverRepository coverRepository;
    private final VolumeRepository volumeRepository;

    public CoverDto findCoverByVolumeId(int volumeId) {
        boolean volumeExists = volumeRepository.existsById(volumeId);
        if (!volumeExists) {
            throw new NotFoundException(ErrorMessages.notFoundVolume(volumeId));
        }

        Cover cover = coverRepository.findByVolumeId(volumeId);
        return coverMapperImpl.toDto(cover);
    }

    public List<CoverDto> findCoversByMangaId(int mangaId) {
        List<Cover> covers = coverRepository.findByMangaId(mangaId);
        return coverMapperImpl.toDto(covers);
    }

    public CoverDto findById(int id) {
        Cover cover = coverRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundCover(id));
                });

        return coverMapperImpl.toDto(cover);
    }

    public CoverDto add(CreateCoverDto payload) {
        boolean exists = coverRepository.existsByVolumeId(
                payload.getVolumeId());

        if (exists) {
            throw new AlredyExistsException(ErrorMessages.coverAlredyExists());
        }

        Volume volume = volumeRepository.findById(payload.getVolumeId())
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundVolume(payload.getVolumeId()));
                });

        Cover cover = coverMapperImpl.toEntity(payload);
        cover.setVolume(volume);

        Cover response = coverRepository.save(cover);

        return coverMapperImpl.toDto(response);
    }

    public CoverDto update(int id, CreateCoverDto payload) {
        Cover cover = coverRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundCover(id));
                });

        Volume volume = volumeRepository.findById(payload.getVolumeId()).orElseThrow(() -> {
            throw new NotFoundException(ErrorMessages.notFoundVolume(id));
        });

        coverMapperImpl.update(cover, payload, volume);

        Cover response = coverRepository.save(cover);

        return coverMapperImpl.toDto(response);
    }

    public void remove(int id) {
        boolean exists = coverRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException(ErrorMessages.notFoundCover(id));
        }

        coverRepository.deleteById(id);
    }
}

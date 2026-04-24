package com.manga.catalog.manga_catalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.dtos.cover.CreateCoverDto;
import com.manga.catalog.manga_catalog.dtos.volume.CreateVolumeDto;
import com.manga.catalog.manga_catalog.dtos.volume.VolumeDto;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.entities.Volume;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.VolumeRepository;
import com.manga.catalog.manga_catalog.shared.exceptions.ErrorMessages;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;
import com.manga.catalog.manga_catalog.shared.mappers.VolumeMapperImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolumeService {

    private final VolumeRepository repository;
    private final CoverService coverService;
    private final VolumeMapperImpl volumeMapperImpl;
    private final MangaRepository mangaRepository;

    public List<VolumeDto> findByMangaId(int mangaId) {
        List<Volume> volumes = repository.findByMangaId(mangaId);

        List<VolumeDto> dto = volumeMapperImpl.toDto(volumes);
        for (VolumeDto volume : dto) {
            CoverDto cover = coverService.findCoverByVolumeId(volume.getId());
            volume.setCover(cover);
        }

        return dto;
    }

    public VolumeDto findById(int id) {
        Volume volume = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundVolume(id));
                });

        CoverDto cover = coverService.findCoverByVolumeId(volume.getId());

        return volumeMapperImpl.toDto(volume, cover);
    }

    public VolumeDto add(CreateVolumeDto payload) {
        validateIfVolumeAlredyInUse(payload.getVolumeNumber());

        Manga manga = mangaRepository.findById(payload.getMangaId())
                .orElseThrow(() -> {
                    throw new AlredyExistsException(ErrorMessages.VolumeAlredyExists());
                });

        Volume volume = volumeMapperImpl.toEntity(payload);
        volume.setManga(manga);

        Volume response = repository.save(volume);

        CreateCoverDto cover = new CreateCoverDto(response.getId(), payload.getCoverUrl());
        CoverDto coverDto = coverService.add(cover);

        return volumeMapperImpl.toDto(response, coverDto);
    }

    public List<VolumeDto> addMany(List<CreateVolumeDto> payload) {
        List<VolumeDto> response = new ArrayList<VolumeDto>();

        for (CreateVolumeDto volume : payload) {
            VolumeDto created = add(volume);
            response.add(created);
        }

        return response;
    }

    public VolumeDto update(int id, CreateVolumeDto payload) {
        Volume volume = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(ErrorMessages.notFoundVolume(id));
                });

        boolean shouldCheckByVolume = volume.getVolumeNumber() != payload.getVolumeNumber();

        if (shouldCheckByVolume) {
            validateIfVolumeAlredyInUse(payload.getVolumeNumber());
        }

        volumeMapperImpl.update(volume, payload);

        Volume response = repository.save(volume);

        CoverDto coverDto = coverService.findCoverByVolumeId(id);
        CreateCoverDto createCover = new CreateCoverDto(response.getId(), payload.getCoverUrl());

        coverService.update(coverDto.getId(), createCover);

        return volumeMapperImpl.toDto(response, coverDto);
    }

    public void remove(int id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new NotFoundException(ErrorMessages.notFoundVolume(id));
        }

        repository.deleteById(id);
    }

    private void validateIfVolumeAlredyInUse(int volumeNumber) {
        boolean exists = repository.existsByVolumeNumber(volumeNumber);

        if (exists) {
            throw new AlredyExistsException(ErrorMessages.VolumeAlredyInUse());
        }
    }
}

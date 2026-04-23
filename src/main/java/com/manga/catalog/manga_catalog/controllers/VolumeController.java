package com.manga.catalog.manga_catalog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manga.catalog.manga_catalog.dtos.volume.CreateVolumeDto;
import com.manga.catalog.manga_catalog.dtos.volume.VolumeDto;
import com.manga.catalog.manga_catalog.services.VolumeService;

import lombok.RequiredArgsConstructor;

@Controller()
@RequiredArgsConstructor
@RequestMapping("/volume")
public class VolumeController {

    private final VolumeService service;

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<VolumeDto>> findByMangaId(@PathVariable int mangaId) {
        List<VolumeDto> volumes = service.findByMangaId(mangaId);
        return new ResponseEntity<List<VolumeDto>>(volumes, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolumeDto> findById(@PathVariable int id) {
        VolumeDto volume = service.findById(id);
        return new ResponseEntity<VolumeDto>(volume, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<VolumeDto> add(@RequestBody CreateVolumeDto body) {
        VolumeDto volume = service.add(body);
        return new ResponseEntity<VolumeDto>(volume, HttpStatus.OK);
    }

    @PostMapping("/many")
    public ResponseEntity<List<VolumeDto>> addMany(@RequestBody List<CreateVolumeDto> body) {
        List<VolumeDto> volume = service.addMany(body);
        return new ResponseEntity<List<VolumeDto>>(volume, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolumeDto> update(
            @PathVariable int id,
            @RequestBody CreateVolumeDto body) {
        VolumeDto volume = service.update(id, body);
        return new ResponseEntity<VolumeDto>(volume, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}

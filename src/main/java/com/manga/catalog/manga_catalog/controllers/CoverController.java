package com.manga.catalog.manga_catalog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manga.catalog.manga_catalog.dtos.cover.CoverDto;
import com.manga.catalog.manga_catalog.services.CoverService;

import lombok.RequiredArgsConstructor;

@Controller()
@RequiredArgsConstructor
@RequestMapping("/cover")
public class CoverController {

    private final CoverService service;

    @GetMapping("/{id}")
    public ResponseEntity<CoverDto> findById(@PathVariable int id) {
        CoverDto cover = service.findById(id);
        return new ResponseEntity<CoverDto>(cover, HttpStatus.OK);
    }

    @GetMapping("/volume/{volumeId}")
    public ResponseEntity<CoverDto> findCoverByVolumeId(@PathVariable int volumeId) {
        CoverDto covers = service.findCoverByVolumeId(volumeId);
        return new ResponseEntity<CoverDto>(covers, HttpStatus.OK);
    }

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<CoverDto>> findCoversByMangaId(@PathVariable int mangaId) {
        List<CoverDto> covers = service.findCoversByMangaId(mangaId);
        return new ResponseEntity<List<CoverDto>>(covers, HttpStatus.OK);
    }
}

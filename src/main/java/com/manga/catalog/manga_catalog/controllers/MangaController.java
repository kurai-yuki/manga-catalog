package com.manga.catalog.manga_catalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manga.catalog.manga_catalog.dtos.CreateMangaDto;
import com.manga.catalog.manga_catalog.dtos.MangaCountDto;
import com.manga.catalog.manga_catalog.dtos.MangaDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.services.MangaService;

@Controller
@RequestMapping("/manga")
public class MangaController {
    @Autowired
    MangaService service;

    @GetMapping
    public ResponseEntity<Page<MangaDto>> findAll(@ModelAttribute Pagination pagination) {
        Page<MangaDto> mangas = service.findAll(pagination);
        return new ResponseEntity<Page<MangaDto>>(mangas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaDto> findById(@PathVariable int id) {
        MangaDto manga = service.findById(id);
        return new ResponseEntity<MangaDto>(manga, HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<MangaCountDto> mangaCount() {
        MangaCountDto count = service.mangaCount();
        return new ResponseEntity<MangaCountDto>(count, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MangaDto> add(@RequestBody CreateMangaDto body) {
        MangaDto manga = service.add(body);
        return new ResponseEntity<MangaDto>(manga, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaDto> update(
            @PathVariable int id,
            @RequestBody CreateMangaDto body) {
        MangaDto manga = service.update(id, body);
        return new ResponseEntity<MangaDto>(manga, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }
}

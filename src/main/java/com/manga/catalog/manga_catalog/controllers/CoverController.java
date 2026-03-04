package com.manga.catalog.manga_catalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.manga.catalog.manga_catalog.dtos.CoverDto;
import com.manga.catalog.manga_catalog.dtos.CreateCoverDto;
import com.manga.catalog.manga_catalog.services.CoverService;

@Controller()
@RequestMapping("/cover")
public class CoverController {

    @Autowired
    CoverService service;

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<CoverDto>> findCoversByMangaId(@PathVariable int mangaId) {
        List<CoverDto> covers = service.findCoversByMangaId(mangaId);
        return new ResponseEntity<List<CoverDto>>(covers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverDto> findById(@PathVariable int id) {
        CoverDto cover = service.findById(id);
        return new ResponseEntity<CoverDto>(cover, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<CoverDto> add(@RequestBody CreateCoverDto body) {
        CoverDto cover = service.add(body);
        return new ResponseEntity<CoverDto>(cover, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoverDto> update(
            @PathVariable int id,
            @RequestBody CreateCoverDto body) {
        CoverDto cover = service.update(id, body);
        return new ResponseEntity<CoverDto>(cover, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }
}

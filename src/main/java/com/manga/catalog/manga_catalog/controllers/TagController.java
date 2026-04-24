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

import com.manga.catalog.manga_catalog.dtos.mangaTag.CreateMangaTagDto;
import com.manga.catalog.manga_catalog.dtos.mangaTag.MangaTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.services.MangaTagService;
import com.manga.catalog.manga_catalog.services.TagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;
    private final MangaTagService mangaTagService;

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() {
        List<TagDto> tags = tagService.findAll();
        return new ResponseEntity<List<TagDto>>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable int id) {
        TagDto tag = tagService.findById(id);
        return new ResponseEntity<TagDto>(tag, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagDto> add(@RequestBody CreateTagDto body) {
        TagDto tag = tagService.add(body);
        return new ResponseEntity<TagDto>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(
            @PathVariable int id,
            @RequestBody CreateTagDto body) {
        TagDto tag = tagService.update(id, body);
        return new ResponseEntity<TagDto>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        tagService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manga-tag/{id}")
    public ResponseEntity<MangaTagDto> findMangaTagById(@PathVariable int id) {
        MangaTagDto mangaTags = mangaTagService.findById(id);
        return new ResponseEntity<MangaTagDto>(mangaTags, HttpStatus.OK);
    }

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<MangaTagDto>> findMangaTags(@PathVariable int mangaId) {
        List<MangaTagDto> mangaTags = mangaTagService.findMangaTags(mangaId);
        return new ResponseEntity<List<MangaTagDto>>(mangaTags, HttpStatus.OK);
    }

    @PostMapping("/manga")
    public ResponseEntity<List<MangaTagDto>> addMangaTags(@RequestBody CreateMangaTagDto body) {
        List<MangaTagDto> mangaTags = mangaTagService.addMangaTags(body);
        return new ResponseEntity<List<MangaTagDto>>(mangaTags, HttpStatus.CREATED);
    }

    @DeleteMapping("/manga/{mangaId}")
    public ResponseEntity<Void> removeMangaTag(@PathVariable int mangaId) {
        mangaTagService.remove(mangaId);
        return ResponseEntity.noContent().build();
    }
}

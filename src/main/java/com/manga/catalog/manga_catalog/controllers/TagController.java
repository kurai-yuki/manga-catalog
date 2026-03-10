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

import com.manga.catalog.manga_catalog.dtos.tag.CreateTagDto;
import com.manga.catalog.manga_catalog.dtos.tag.TagDto;
import com.manga.catalog.manga_catalog.services.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService service;

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() {
        List<TagDto> tags = service.findAll();
        return new ResponseEntity<List<TagDto>>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable int id) {
        TagDto tag = service.findById(id);
        return new ResponseEntity<TagDto>(tag, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<TagDto> add(@RequestBody CreateTagDto body) {
        TagDto tag = service.add(body);
        return new ResponseEntity<TagDto>(tag, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(
            @PathVariable int id,
            @RequestBody CreateTagDto body) {
        TagDto tag = service.update(id, body);
        return new ResponseEntity<TagDto>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}

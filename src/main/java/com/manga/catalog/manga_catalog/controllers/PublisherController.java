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

import com.manga.catalog.manga_catalog.dtos.CreatePublisherDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.dtos.PublisherDto;
import com.manga.catalog.manga_catalog.services.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    PublisherService service;

    @GetMapping
    public ResponseEntity<Page<PublisherDto>> findAll(@ModelAttribute Pagination pagination) {
        Page<PublisherDto> publishers = service.findAll(pagination);
        return new ResponseEntity<Page<PublisherDto>>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable int id) {
        PublisherDto publisher = service.findById(id);
        return new ResponseEntity<PublisherDto>(publisher, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<PublisherDto> add(@RequestBody CreatePublisherDto body) {
        PublisherDto publisher = service.add(body);
        return new ResponseEntity<PublisherDto>(publisher, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> update(
            @PathVariable int id,
            @RequestBody CreatePublisherDto body) {
        PublisherDto publisher = service.update(id, body);
        return new ResponseEntity<PublisherDto>(publisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }
}

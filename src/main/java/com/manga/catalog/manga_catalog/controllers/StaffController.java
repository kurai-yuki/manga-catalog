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

import com.manga.catalog.manga_catalog.dtos.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.Pagination;
import com.manga.catalog.manga_catalog.dtos.StaffDto;
import com.manga.catalog.manga_catalog.services.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService service;

    @GetMapping
    public ResponseEntity<Page<StaffDto>> findAll(@ModelAttribute Pagination pagination) {
        Page<StaffDto> publishers = service.findAll(pagination);
        return new ResponseEntity<Page<StaffDto>>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> findById(@PathVariable int id) {
        StaffDto publisher = service.findById(id);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<StaffDto> add(@RequestBody CreateStaffDto body) {
        StaffDto publisher = service.add(body);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> update(
            @PathVariable int id,
            @RequestBody CreateStaffDto body) {
        StaffDto publisher = service.update(id, body);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }
}

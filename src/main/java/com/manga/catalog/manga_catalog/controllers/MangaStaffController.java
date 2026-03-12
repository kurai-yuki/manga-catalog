package com.manga.catalog.manga_catalog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manga.catalog.manga_catalog.dtos.mangaStaff.CreateMangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.MangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.UpdateMangaStaffDto;
import com.manga.catalog.manga_catalog.services.MangaStaffService;

import lombok.RequiredArgsConstructor;

@Controller()
@RequiredArgsConstructor
@RequestMapping("/manga-staff")
public class MangaStaffController {

    private final MangaStaffService service;

    @GetMapping("/manga/{id}")
    public ResponseEntity<List<MangaStaffDto>> findAllMangaStaffById(@PathVariable int id) {
        List<MangaStaffDto> mangaStaff = service.findAllMangaStaffById(id);
        return new ResponseEntity<List<MangaStaffDto>>(mangaStaff, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaStaffDto> findById(@PathVariable int id) {
        MangaStaffDto covers = service.findById(id);
        return new ResponseEntity<MangaStaffDto>(covers, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<MangaStaffDto> add(@RequestBody CreateMangaStaffDto body) {
        MangaStaffDto mangaStaff = service.add(body);
        return new ResponseEntity<MangaStaffDto>(mangaStaff, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody UpdateMangaStaffDto body) {
        service.updateStaffRole(id, body.getRole());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}

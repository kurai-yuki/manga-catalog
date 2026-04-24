package com.manga.catalog.manga_catalog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manga.catalog.manga_catalog.dtos.mangaStaff.CreateMangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.MangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.UpdateMangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.staff.CreateStaffDto;
import com.manga.catalog.manga_catalog.dtos.staff.StaffDto;
import com.manga.catalog.manga_catalog.services.MangaStaffService;
import com.manga.catalog.manga_catalog.services.StaffService;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationRequest;
import com.manga.catalog.manga_catalog.shared.dtos.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;
    private final MangaStaffService mangaStaffService;

    @GetMapping
    public ResponseEntity<PaginationResponse<StaffDto>> findAll(@ModelAttribute PaginationRequest pagination) {
        PaginationResponse<StaffDto> publishers = staffService.findAll(pagination);
        return new ResponseEntity<PaginationResponse<StaffDto>>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> findById(@PathVariable int id) {
        StaffDto publisher = staffService.findById(id);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<StaffDto> add(@RequestBody CreateStaffDto body) {
        StaffDto publisher = staffService.add(body);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> update(
            @PathVariable int id,
            @RequestBody CreateStaffDto body) {
        StaffDto publisher = staffService.update(id, body);
        return new ResponseEntity<StaffDto>(publisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        staffService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manga/{id}")
    public ResponseEntity<List<MangaStaffDto>> findAllMangaStaff(@PathVariable int id) {
        List<MangaStaffDto> mangaStaff = mangaStaffService.findAllMangaStaff(id);
        return new ResponseEntity<List<MangaStaffDto>>(mangaStaff, HttpStatus.OK);
    }

    @GetMapping("/manga-staff/{id}")
    public ResponseEntity<MangaStaffDto> findMangaStaffById(@PathVariable int id) {
        MangaStaffDto covers = mangaStaffService.findMangaStaffById(id);
        return new ResponseEntity<MangaStaffDto>(covers, HttpStatus.OK);
    }

    @PostMapping("/manga")
    public ResponseEntity<MangaStaffDto> addStaffOnManga(@RequestBody CreateMangaStaffDto body) {
        MangaStaffDto mangaStaff = mangaStaffService.addStaffOnManga(body);
        return new ResponseEntity<MangaStaffDto>(mangaStaff, HttpStatus.CREATED);
    }

    @PatchMapping("/manga/{id}")
    public ResponseEntity<Void> updateStaffRole(
            @PathVariable int id,
            @RequestBody UpdateMangaStaffDto body) {
        mangaStaffService.updateStaffRole(id, body.getRole());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/manga/{id}")
    public ResponseEntity<Void> removeMangaStaff(@PathVariable int id) {
        mangaStaffService.removeMangaStaff(id);
        return ResponseEntity.noContent().build();
    }
}

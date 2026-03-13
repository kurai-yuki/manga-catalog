package com.manga.catalog.manga_catalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manga.catalog.manga_catalog.dtos.mangaStaff.CreateMangaStaffDto;
import com.manga.catalog.manga_catalog.dtos.mangaStaff.MangaStaffDto;
import com.manga.catalog.manga_catalog.entities.Manga;
import com.manga.catalog.manga_catalog.entities.MangaStaff;
import com.manga.catalog.manga_catalog.entities.Staff;
import com.manga.catalog.manga_catalog.enums.RoleEnum;
import com.manga.catalog.manga_catalog.mappers.MangaStaffMapperImpl;
import com.manga.catalog.manga_catalog.repositories.MangaRepository;
import com.manga.catalog.manga_catalog.repositories.MangaStaffRepository;
import com.manga.catalog.manga_catalog.repositories.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MangaStaffService {

    private final MangaStaffRepository mangaStaffRepository;
    private final MangaRepository mangaRepository;
    private final StaffRepository staffRepository;
    private final MangaStaffMapperImpl mangaStaffMapperImpl;

    public List<MangaStaffDto> findAllMangaStaff(int mangaId) {
        List<MangaStaff> staffMembers = mangaStaffRepository.findAllByMangaId(mangaId);

        List<MangaStaffDto> dto = mangaStaffMapperImpl.toDto(staffMembers);
        return dto;
    }

    public MangaStaffDto findMangaStaffById(int mangaStaffId) {
        MangaStaff staffMember = mangaStaffRepository.findById(mangaStaffId)
                .orElseThrow(() -> {
                    throw new Error("exists1");
                });

        return mangaStaffMapperImpl.toDto(staffMember);
    }

    public MangaStaffDto addStaffOnManga(CreateMangaStaffDto payload) {
        Manga manga = mangaRepository.findById(payload.getMangaId())
                .orElseThrow(() -> {
                    throw new Error("exists1");
                });

        Staff staff = staffRepository.findById(payload.getStaffId())
                .orElseThrow(() -> {
                    throw new Error("exists2");
                });

        MangaStaff staffMember = mangaStaffMapperImpl.toEntity(payload);
        staffMember.setManga(manga);
        staffMember.setStaff(staff);

        MangaStaff response = mangaStaffRepository.save(staffMember);
        return mangaStaffMapperImpl.toDto(response);
    }

    public void updateStaffRole(int id, RoleEnum role) {
        MangaStaff mangaStaff = mangaStaffRepository.findById(id)
                .orElseThrow(() -> {
                    throw new Error("exists");
                });
        mangaStaff.setRole(role);

        mangaStaffRepository.save(mangaStaff);
    }

    public void removeMangaStaff(int id) {
        boolean exists = mangaStaffRepository.existsById(id);

        if (!exists) {
            throw new Error("exists");
        }

        try {
            mangaStaffRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error("Delete failed");
        }
    }
}

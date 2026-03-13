package com.manga.catalog.manga_catalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.MangaStaff;
import com.manga.catalog.manga_catalog.shared.enums.RoleEnum;

public interface MangaStaffRepository extends JpaRepository<MangaStaff, Integer> {
    public List<MangaStaff> findAllByMangaId(int mangaId);

    public MangaStaff findByRole(RoleEnum role);

    boolean existsByStaffIdAndMangaIdAndRole(Integer staffId, Integer mangaId, RoleEnum role);
}

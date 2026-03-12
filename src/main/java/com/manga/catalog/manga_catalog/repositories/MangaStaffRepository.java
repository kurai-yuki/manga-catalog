package com.manga.catalog.manga_catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.catalog.manga_catalog.entities.MangaStaff;
import java.util.List;
import com.manga.catalog.manga_catalog.enums.StaffRoleEnum;

public interface MangaStaffRepository extends JpaRepository<MangaStaff, Integer> {
    public List<MangaStaff> findAllByMangaId(int mangaId);

    public MangaStaff findByRole(StaffRoleEnum role);

    boolean existsByStaffIdAndMangaIdAndRole(Integer staffId, Integer mangaId, StaffRoleEnum role);
}

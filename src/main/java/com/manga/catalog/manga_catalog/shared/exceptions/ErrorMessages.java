package com.manga.catalog.manga_catalog.shared.exceptions;

public class ErrorMessages {
    public static String notFoundManga(int id) {
        return "Manga with id " + id + " not founded!";
    }

    public static String mangaAlredyExists() {
        return "Manga with this name alredy exists!";
    }

    public static String notFoundPublisher(int id) {
        return "Publisher with id " + id + " not founded!";
    }

    public static String publisherAlredyExists() {
        return "Publisher with this name alredy exists!";
    }

    public static String notFoundCover(int id) {
        return "Cover with id " + id + " not founded!";
    }

    public static String coverAlredyExists() {
        return "Cover alredy exists on this volume number!";
    }

    public static String notFoundStaff(int id) {
        return "Staff member with id " + id + " not founded!";
    }

    public static String staffAlredyExists() {
        return "Staff member with this name alredy exists!";
    }

    public static String notFoundTag(int id) {
        return "Tag with id " + id + " not founded!";
    }

    public static String tagAlredyExists() {
        return "Tag with this name alredy exists!";
    }

    public static String notFoundMangaStaff(int id) {
        return "Manga staff with id " + id + " not founded!";
    }

    public static String mangaStaffAlredyExists() {
        return "Staff member with this role alredy exists on this manga!";
    }

    public static String notFoundMangaTag(int id) {
        return "Manga tag with id " + id + " not founded!";
    }

    public static String notFoundAnyMangaTag() {
        return "Not founded any tag with those ids!";
    }

    public static String notFoundSomeMangaTag() {
        return "Some tags not founded with those ids!";
    }

    public static String mangaTagAlredyExists() {
        return "Tag alredy exists on this manga!";
    }

    public static String notFoundVolume(int id) {
        return "Volume with id " + id + " not founded!";
    }

    public static String VolumeAlredyExists() {
        return "Volume alredy exists on this manga!";
    }

    public static String VolumeAlredyInUse() {
        return "Volume alredy exists on this manga with this number!";
    }
}

package com.manga.catalog.manga_catalog.shared.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlredyExistsException extends RuntimeException {
    private final HttpStatusCode statusCode = HttpStatus.CONFLICT;

    public AlredyExistsException(String message) {
        super(message);
    }
}

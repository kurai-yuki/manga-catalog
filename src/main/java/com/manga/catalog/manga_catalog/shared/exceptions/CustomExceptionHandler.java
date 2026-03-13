package com.manga.catalog.manga_catalog.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.manga.catalog.manga_catalog.shared.dtos.ErrorDto;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.AlredyExistsException;
import com.manga.catalog.manga_catalog.shared.exceptions.customExceptions.NotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException exception) {
        HttpStatusCode statusCode = exception.getStatusCode();
        ErrorDto error = new ErrorDto(exception.getMessage(), statusCode.value());

        return new ResponseEntity<ErrorDto>(error, statusCode);
    }

    @ExceptionHandler(value = AlredyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlredyExistsException(AlredyExistsException exception) {
        HttpStatusCode statusCode = exception.getStatusCode();
        ErrorDto error = new ErrorDto(exception.getMessage(), statusCode.value());

        return new ResponseEntity<ErrorDto>(error, statusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleInternalError(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDto dto = new ErrorDto(ex.getMessage(), statusCode.value());

        return new ResponseEntity<ErrorDto>(dto, statusCode);
    }

}

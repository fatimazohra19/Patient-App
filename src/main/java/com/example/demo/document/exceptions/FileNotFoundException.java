package com.example.demo.document.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FileNotFoundException extends ResponseStatusException {

    public FileNotFoundException(String reason, Throwable cause) {
        super(404,reason, cause);
    }

    public FileNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
    public FileNotFoundException() {
        this("File not found");
    }

    public FileNotFoundException(HttpStatusCode status) {
        super(status);
    }

    public FileNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public FileNotFoundException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }


}

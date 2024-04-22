package com.example.demo.document.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class StorageException extends ResponseStatusException {

    public StorageException( String reason, Throwable cause) {
        super(400,reason, cause);
    }

    public StorageException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
    public StorageException(HttpStatusCode status) {
        super(status);
    }

    public StorageException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public StorageException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }


}

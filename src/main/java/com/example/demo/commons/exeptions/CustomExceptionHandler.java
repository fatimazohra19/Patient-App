package com.example.demo.commons.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

   // @ExceptionHandler(NoSuchElementException.class)
    //public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        //System.out.println(ex);
      //  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    //}


    @ExceptionHandler(NoSuchElementException.class)

    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

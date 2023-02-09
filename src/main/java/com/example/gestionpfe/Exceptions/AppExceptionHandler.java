package com.example.gestionpfe.Exceptions;

import com.example.gestionpfe.Responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {exception.class})
    public ResponseEntity<Object> handleException(exception ex, WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleOthersException(Exception ex, WebRequest webRequest){
//        ErrorResponse response = new ErrorResponse(new Date(), ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

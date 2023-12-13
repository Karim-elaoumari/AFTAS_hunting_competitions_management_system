package com.aftas_backend.handlers.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    private Object data;

    public ResponseMessage(int statusCode,Object data,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }
    public ResponseMessage(int statusCode,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
    public int getStatusCode() {return statusCode;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public String getMessage() {
        return message;
    }
    public Object getData() {
        return data;
    }
    public static ResponseEntity ok(Object data, String message){
           return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(),data,message),HttpStatus.OK);
    }
    public static ResponseEntity notFound(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.NOT_FOUND.value(),message),HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity created(Object data, String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED.value(),data,message),HttpStatus.CREATED);
    }
    public static ResponseEntity badRequest(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(),message),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity badRequest(String message,Object data){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(),data,message),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity unauthorized(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(),message),HttpStatus.UNAUTHORIZED);
    }
    public static ResponseEntity forbidden(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.FORBIDDEN.value(),message),HttpStatus.FORBIDDEN);
    }
    public static ResponseEntity noContent(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.NO_CONTENT.value(),message),HttpStatus.NO_CONTENT);
    }



}

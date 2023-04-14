package com.skypro.pastebin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceEx {

    @ExceptionHandler(PastebinNotFoundException.class)
    public ResponseEntity<?> PastebinNotFound() { return ResponseEntity.notFound().build();}
}

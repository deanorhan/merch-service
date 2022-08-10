package org.daemio.merch.controller;

import org.daemio.merch.error.MerchNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MerchExceptionHandler {
    
    @ExceptionHandler(MerchNotFoundException.class)
    public ResponseEntity<Void> handleMerchNotFound(MerchNotFoundException exception) {
        log.error(exception.getMessage());

        return ResponseEntity.notFound().build();
    }
}

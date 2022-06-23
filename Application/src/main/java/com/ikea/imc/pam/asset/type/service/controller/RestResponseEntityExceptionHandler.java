package com.ikea.imc.pam.asset.type.service.controller;

import com.ikea.imc.pam.asset.type.service.controller.dto.ResponseEntityFactory;
import com.ikea.imc.pam.asset.type.service.exception.RequestException;
import com.ikea.imc.pam.common.dto.ResponseMessageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    
    private static final Logger log = LogManager.getLogger(RestResponseEntityExceptionHandler.class);
    
    @ExceptionHandler(RequestException.class)
    protected ResponseEntity<ResponseMessageDTO<Object>> handleRequestException(RequestException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntityFactory.generateResponse(ex, ex.getMessage());
    }
    
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ResponseMessageDTO<Object>> handleAllOtherExceptions(Throwable ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntityFactory.generateResponse(ex);
    }
}

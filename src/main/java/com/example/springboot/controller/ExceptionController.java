package com.example.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    protected static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        log.info("IllegalArgumentException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();
    }
}

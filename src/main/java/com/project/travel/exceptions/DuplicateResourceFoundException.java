package com.project.travel.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateResourceFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(DuplicateResourceFoundException.class);

    public DuplicateResourceFoundException(String message) {
        super(message);
        logger.error(message);
    }
}

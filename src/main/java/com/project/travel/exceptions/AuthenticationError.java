package com.project.travel.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationError extends RuntimeException {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(AuthenticationError.class);

    public AuthenticationError(String message) {
        super(message);
        logger.error(message);
    }
}


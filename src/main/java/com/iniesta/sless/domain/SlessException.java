package com.iniesta.sless.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SlessException extends RuntimeException {

    public SlessException() {
        super();
    }

    public SlessException(String message) {
        super(message);
    }

    public SlessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SlessException(Throwable cause) {
        super(cause);
    }
}

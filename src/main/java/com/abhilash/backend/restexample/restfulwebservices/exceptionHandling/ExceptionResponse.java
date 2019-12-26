package com.abhilash.backend.restexample.restfulwebservices.exceptionHandling;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;

    public Date getTimestamp() {
        return timestamp;
    }


    public String getMessage() {
        return message;
    }


    public String getDetails() {
        return details;
    }

    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}

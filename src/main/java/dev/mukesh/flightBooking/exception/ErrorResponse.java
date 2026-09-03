package dev.mukesh.flightBooking.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private int status;
    private String error;
    private String message;

    private Map<String, String> validationErrors;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(int status, String error, String message, Map<String, String> validationErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.validationErrors = validationErrors;
    }

}

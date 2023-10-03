package com.alura.foroalura.infra.exception;

public class CustomErrorResponse {
    private int errorCode;
    private String errorMessage;
    private long timestamp;

    public CustomErrorResponse() {
        // Constructor sin argumentos
    }

    public CustomErrorResponse(int status, String message) {
        this.errorCode = status;
        this.errorMessage = message;
    }

    public CustomErrorResponse(int status, String message, long timestamp) {
        this.errorCode = status;
        this.errorMessage = message;
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int status) {
        this.errorCode = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}

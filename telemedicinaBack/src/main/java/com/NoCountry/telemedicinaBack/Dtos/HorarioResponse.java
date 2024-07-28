package com.NoCountry.telemedicinaBack.Dtos;

public class HorarioResponse {

    private boolean success;
    private String message;

    public HorarioResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public HorarioResponse(){

    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

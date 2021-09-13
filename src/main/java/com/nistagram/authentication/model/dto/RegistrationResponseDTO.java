package com.nistagram.authentication.model.dto;

public class RegistrationResponseDTO {
    private String status;

    public RegistrationResponseDTO() {
    }

    public RegistrationResponseDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

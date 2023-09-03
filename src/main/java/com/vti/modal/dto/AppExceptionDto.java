package com.vti.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppExceptionDto {
    private String message;
    private int status;
    private String path;
    private Instant timestamp;


    public AppExceptionDto(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = Instant.now();
    }
}

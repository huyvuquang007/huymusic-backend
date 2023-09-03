package com.vti.modal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountCreateDto {
    @NotBlank(message = "ko để trống")
    private String fullName;
    @NotBlank(message = "ko để trống")
    private String email;
    @NotBlank(message = "ko để trống")
    private String username;
    @NotBlank(message = "ko để trống")
    private String password;
}

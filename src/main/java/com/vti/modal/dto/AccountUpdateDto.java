package com.vti.modal.dto;

import lombok.Data;

@Data
public class AccountUpdateDto {
    private int id;
    private String fullName;
    private String email;
    private String username;
    private String password;
}

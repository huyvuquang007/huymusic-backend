package com.vti.modal.dto;

import com.vti.modal.entity.Role;
import lombok.Data;

@Data
public class LoginDto {
    private int id;
    private String username;
    private Role role;
    private String fullName;
    private String token;
}

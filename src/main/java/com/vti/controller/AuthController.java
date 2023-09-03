package com.vti.controller;

import com.vti.exception.CustomException;
import com.vti.exception.ErrorResponseEnum;
import com.vti.modal.dto.LoginDto;
import com.vti.modal.entity.Account;
import com.vti.repository.AccountRepository;
import com.vti.service.JWTTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(value = "*")
public class AuthController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @PostMapping("/login-jwt")
    public LoginDto loginJWT (@RequestParam String username ,@RequestParam String password){
        Account account = accountRepository.findByUsername(username);
        if (account == null){
            throw new CustomException(ErrorResponseEnum.LOGIN_FAIL_USERNAME);
        }

        if (!passwordEncoder.matches(password, account.getPassword())){
            throw new CustomException(ErrorResponseEnum.LOGIN_FAIL_PASSWORD);
        }
        LoginDto loginDto = new LoginDto();

        BeanUtils.copyProperties(account, loginDto);
        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }
}

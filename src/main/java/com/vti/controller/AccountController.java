package com.vti.controller;

import com.vti.modal.dto.AccountCreateDto;
import com.vti.modal.dto.AccountUpdateDto;
import com.vti.modal.entity.Account;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@CrossOrigin(value = "*")
@Validated
public class AccountController {
    @Autowired
    IAccountService accountService;

    @GetMapping("/get-all-account")
    List<Account> getAllAccount (){
        return accountService.getListAccount();
    }

    @GetMapping("/{id}")
    Account getById(@PathVariable int id){
        return accountService.getById(id);
    }

    @PostMapping("/create-account")
    Account createAccount(@RequestBody @Valid AccountCreateDto accountCreateDto){
        return accountService.createAccount(accountCreateDto);
    }

    @PutMapping("/update-account")
    Account updateAccount(@RequestBody AccountUpdateDto accountUpdateDto){
        return accountService.updateAccount(accountUpdateDto);
    }

    @DeleteMapping("{id}")
    String deleteAccount(@PathVariable int id){
        return accountService.deleteAccount(id);
    }
}

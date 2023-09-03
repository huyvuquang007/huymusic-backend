package com.vti.service;

import com.vti.modal.dto.AccountCreateDto;
import com.vti.modal.dto.AccountUpdateDto;
import com.vti.modal.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getListAccount();
    Account getById(int id);
    Account createAccount (AccountCreateDto accountCreateDto);
    Account updateAccount (AccountUpdateDto accountUpdateDto);
    String deleteAccount (int id);

}

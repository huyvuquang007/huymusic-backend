package com.vti.service;

import com.vti.exception.CustomException;
import com.vti.exception.ErrorResponseEnum;
import com.vti.modal.dto.AccountCreateDto;
import com.vti.modal.dto.AccountUpdateDto;
import com.vti.modal.entity.Account;
import com.vti.modal.entity.Role;
import com.vti.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Account> getListAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()){
            return accountOptional.get();
        } else  throw new CustomException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);

    }

    @Override
    public Account createAccount(AccountCreateDto accountCreateDto) {
        if (accountRepository.existsByUsername(accountCreateDto.getUsername())){
            throw new CustomException(ErrorResponseEnum.USERNAME_EXISTED);
        }else {
            Account account = new Account(accountCreateDto);
            account.setRole(Role.USER);
            account.setPassword(passwordEncoder.encode(accountCreateDto.getPassword()));
            return accountRepository.save(account);
        }

    }

    @Override
    public Account updateAccount(AccountUpdateDto accountUpdateDto) {
        if (accountRepository.existsById(accountUpdateDto.getId())){
            Account account = new Account(accountUpdateDto);
           return accountRepository.save(account);
        }else throw new CustomException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
    }

    @Override
    public String deleteAccount(int id) {
        if (accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            return "xóa thành công";
        }else return "xóa thất bại";
    }
}

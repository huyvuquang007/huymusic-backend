package com.vti.repository;

import com.vti.modal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByUsername (String username);
    boolean existsById (int id);
    Account findByUsername(String username);
}

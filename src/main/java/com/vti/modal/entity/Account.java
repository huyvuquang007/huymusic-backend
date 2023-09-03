package com.vti.modal.entity;

import com.vti.modal.dto.AccountCreateDto;
import com.vti.modal.dto.AccountUpdateDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`account`")
public class Account extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name = "username", unique = true , nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Account(AccountCreateDto accountCreateDto) {
        this.fullName = accountCreateDto.getFullName();
        this.email = accountCreateDto.getEmail();
        this.username = accountCreateDto.getUsername();
        this.password = accountCreateDto.getPassword();
    }

    public Account(AccountUpdateDto accountUpdateDto) {
        this.id = accountUpdateDto.getId();
        this.fullName = accountUpdateDto.getFullName();
        this.email = accountUpdateDto.getEmail();
        this.username = accountUpdateDto.getUsername();
        this.password = accountUpdateDto.getPassword();
    }
}

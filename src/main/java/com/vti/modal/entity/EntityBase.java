package com.vti.modal.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass //  để đánh đấu là 1 phần của các class khác
public class EntityBase {
    @Column(name = "create_by")
    protected String createBy;

    @Column (name = "create_at")
    protected Date createAt;

    @Column (name = "update_by")
    protected String updateBy;

    @Column (name = "update_at")
    protected Date updateAt;

    // hàm đc gọi tới khi đối tượng entity được thêm mới
    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
        try {
            this.createBy = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (Exception e){
            this.createBy = "User chưa đăng nhập";
        }
    }


    // hàm đc gọi tới khi đối tượng entity được Update
    @PreUpdate
    public void preUpdate(){
        this.updateAt = new Date();
        try {
            this.updateBy = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (Exception e){
            this.updateBy = "User chưa đăng nhập";
        }
    }
}
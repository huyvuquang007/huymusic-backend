package com.vti.repository;

import com.vti.modal.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer,Integer> , JpaSpecificationExecutor<Singer> {
    Singer findByName(String name);
    boolean existsByName(String name);
}

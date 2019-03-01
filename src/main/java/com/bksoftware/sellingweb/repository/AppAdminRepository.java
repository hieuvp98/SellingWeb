package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.AppAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppAdminRepository extends CrudRepository<AppAdmin,Integer> {
    AppAdmin findByUsername(String username);
    List<AppAdmin> findAll();
}

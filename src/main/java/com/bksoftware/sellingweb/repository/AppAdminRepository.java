package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.AppAdmin;
import org.springframework.data.repository.CrudRepository;

public interface AppAdminRepository extends CrudRepository<AppAdmin,Integer> {
    AppAdmin findByUsername(String username);
}

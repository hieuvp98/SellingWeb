package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.BigCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {

    BigCategory findByName(String name);

    BigCategory findById(int id);

}

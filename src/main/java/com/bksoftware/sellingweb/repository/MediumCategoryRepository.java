package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.MediumCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediumCategoryRepository extends JpaRepository<MediumCategory,Integer> {

    MediumCategory findByName(String name);

    MediumCategory findById(int id);
}

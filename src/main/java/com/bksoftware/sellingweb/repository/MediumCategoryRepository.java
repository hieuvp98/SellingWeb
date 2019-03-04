package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediumCategoryRepository extends JpaRepository<MediumCategory,Integer> {

    List<MediumCategory> findAllByBigCategory(BigCategory bigCategory);
    MediumCategory findById(int id);
}

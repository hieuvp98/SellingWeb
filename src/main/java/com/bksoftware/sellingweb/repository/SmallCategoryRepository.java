package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.MediumCategory;
import com.bksoftware.sellingweb.entities.SmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findAllByMediumCategory(MediumCategory mediumCategory);

}

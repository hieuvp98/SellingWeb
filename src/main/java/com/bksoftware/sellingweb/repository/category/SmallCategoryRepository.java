package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findAllByMediumCategory(MediumCategory mediumCategory);

    SmallCategory findById(int id);
}

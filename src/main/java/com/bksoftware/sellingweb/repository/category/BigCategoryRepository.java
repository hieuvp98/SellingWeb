package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {

    BigCategory findByName(String name);

    BigCategory findById(int id);

}

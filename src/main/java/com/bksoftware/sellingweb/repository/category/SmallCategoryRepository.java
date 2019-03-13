package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findAllByMediumCategory(MediumCategory mediumCategory);

    SmallCategory findById(int id);

    @Query("select s from SmallCategory s where s.status = true and s.mediumCategory.id= :id")
    public List<SmallCategory> showSmallCategory(@Param("id") int id);
}

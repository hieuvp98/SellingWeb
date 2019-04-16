package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediumCategoryRepository extends JpaRepository<MediumCategory,Integer> {

    List<MediumCategory> findAllByBigCategory(BigCategory bigCategory);
    MediumCategory findById(int id);

    @Query("select m from MediumCategory m where m.status = true and  m.bigCategory.id= :id")
    List<MediumCategory> showMediumCaegory(@Param("id") int id);

    List<MediumCategory> findByStatus(boolean status);
}

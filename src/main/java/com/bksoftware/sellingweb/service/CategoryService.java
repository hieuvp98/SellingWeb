package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.BigCategory;
import com.bksoftware.sellingweb.entities.MediumCategory;
import com.bksoftware.sellingweb.entities.Product;
import com.bksoftware.sellingweb.entities.SmallCategory;

import java.util.List;

public interface CategoryService {

    List<BigCategory> findAllBigCategory();

    List<MediumCategory> findAllMediumCategoryByBigCategory(BigCategory bigCategory);

    List<SmallCategory> findAllSmallCategoryByMediumCategory(MediumCategory mediumCategory);

    List<Product> findAllProductBySmallCategory(SmallCategory smallCategory);

    BigCategory findBigCategoryById(int id);

    MediumCategory findMediumCategoryById(int id);

    SmallCategory findSmallCategoryById(int id);

    boolean saveBigCategory(BigCategory bigCategory);

    boolean saveMediumCategory(MediumCategory mediumCategory);

    boolean saveSmallCategory(SmallCategory smallCategory);

    void deleteBigCategory(BigCategory bigCategory);

    void deleteMediumCategory(MediumCategory mediumCategory);

    void deleteSmallCategory(SmallCategory smallCategory);
}

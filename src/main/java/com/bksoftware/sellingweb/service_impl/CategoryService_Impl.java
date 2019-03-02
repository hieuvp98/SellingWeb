package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.BigCategory;
import com.bksoftware.sellingweb.entities.MediumCategory;
import com.bksoftware.sellingweb.entities.Product;
import com.bksoftware.sellingweb.entities.SmallCategory;
import com.bksoftware.sellingweb.repository.BigCategoryRepository;
import com.bksoftware.sellingweb.repository.MediumCategoryRepository;
import com.bksoftware.sellingweb.repository.ProductRepository;
import com.bksoftware.sellingweb.repository.SmallCategoryRepository;
import com.bksoftware.sellingweb.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService_Impl implements CategoryService {

    private final BigCategoryRepository bigCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final ProductRepository productRepository;

    public CategoryService_Impl(BigCategoryRepository bigCategoryRepository, MediumCategoryRepository mediumCategoryRepository,
                                SmallCategoryRepository smallCategoryRepository,ProductRepository productRepository) {
        this.bigCategoryRepository = bigCategoryRepository;
        this.mediumCategoryRepository = mediumCategoryRepository;
        this.smallCategoryRepository = smallCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<BigCategory> findAllBigCategory() {
        return bigCategoryRepository.findAll();
    }

    @Override
    public List<MediumCategory> findAllMediumCategoryByBigCategory(BigCategory bigCategory) {
        return mediumCategoryRepository.findAllByBigCategory(bigCategory);
    }

    @Override
    public List<SmallCategory> findAllSmallCategoryByMediumCategory(MediumCategory mediumCategory) {
        return smallCategoryRepository.findAllByMediumCategory(mediumCategory);
    }

    @Override
    public List<Product> findAllProductBySmallCategory(SmallCategory smallCategory) {
        return productRepository.findAllBySmallCategory(smallCategory);
    }

    @Override
    public void saveBigCategory(BigCategory bigCategory) {
        bigCategoryRepository.save(bigCategory);
    }

    @Override
    public void saveMediumCategory(MediumCategory mediumCategory) {
        mediumCategoryRepository.save(mediumCategory);
    }

    @Override
    public void saveSmallCategory(SmallCategory smallCategory) {
        smallCategoryRepository.save(smallCategory);
    }


    @Override
    public void deleteBigCategory(BigCategory bigCategory) {
        bigCategory.setStatus(false);
        bigCategoryRepository.save(bigCategory);
    }

    @Override
    public void deleteMediumCategory(MediumCategory mediumCategory) {
        mediumCategory.setStatus(false);
        mediumCategoryRepository.save(mediumCategory);
    }

    @Override
    public void deleteSmallCategory(SmallCategory smallCategory) {
        smallCategory.setStatus(false);
        smallCategoryRepository.save(smallCategory);
    }
}

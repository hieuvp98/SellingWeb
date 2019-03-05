package com.bksoftware.sellingweb.service_impl.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.repository.category.BigCategoryRepository;
import com.bksoftware.sellingweb.repository.category.MediumCategoryRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.repository.category.SmallCategoryRepository;
import com.bksoftware.sellingweb.service.category.CategoryService;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CategoryService_Impl implements CategoryService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final BigCategoryRepository bigCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final ProductRepository productRepository;

    public CategoryService_Impl(BigCategoryRepository bigCategoryRepository, MediumCategoryRepository mediumCategoryRepository,
                                SmallCategoryRepository smallCategoryRepository, ProductRepository productRepository) {
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
    public BigCategory findBigCategoryById(int id) {
        return bigCategoryRepository.findById(id);
    }

    @Override
    public MediumCategory findMediumCategoryById(int id) {
        return mediumCategoryRepository.findById(id);
    }

    @Override
    public SmallCategory findSmallCategoryById(int id) {
        return smallCategoryRepository.findById(id);
    }

    @Override
    public boolean saveBigCategory(BigCategory bigCategory) {
        try {
            bigCategoryRepository.save(bigCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveMediumCategory(MediumCategory mediumCategory) {
        try {
            mediumCategoryRepository.save(mediumCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveSmallCategory(SmallCategory smallCategory) {
        try {
            smallCategoryRepository.save(smallCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-error", ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean deleteBigCategory(BigCategory bigCategory) {
        try {
            bigCategory.setStatus(false);
            bigCategoryRepository.save(bigCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteMediumCategory(MediumCategory mediumCategory) {
        try {
            mediumCategory.setStatus(false);
            mediumCategoryRepository.save(mediumCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSmallCategory(SmallCategory smallCategory) {
        try {
            smallCategory.setStatus(false);
            smallCategoryRepository.save(smallCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
            return false;
        }
    }
}

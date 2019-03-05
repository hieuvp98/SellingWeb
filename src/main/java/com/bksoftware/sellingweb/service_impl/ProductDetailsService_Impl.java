package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.entities.product.ProductImage;
import com.bksoftware.sellingweb.repository.product.FeatureRepository;
import com.bksoftware.sellingweb.repository.product.FeedbackRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductImageRepository;
import com.bksoftware.sellingweb.service.ProductDetailsService;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductDetailsService_Impl implements ProductDetailsService {

    private final static Logger LOGGER = Logger.getLogger(ProductDetailsService_Impl.class.getName());

    private final ProductDetailsRepository productDetailsRepository;
    private final FeatureRepository featureRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDetailsService_Impl(ProductDetailsRepository productDetailsRepository, FeatureRepository featureRepository, FeedbackRepository feedbackRepository, ProductImageRepository productImageRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.featureRepository = featureRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public boolean saveProductDetails(ProductDetails productDetails) {
        try {
            productDetailsRepository.save(productDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-productDetails-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public ProductDetails findById(int id) {
        return productDetailsRepository.findById(id);
    }

    @Override
    public boolean saveFeature(Feature feature) {
        try {
            featureRepository.save(feature);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-feature-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFeature(Feature feature) {
        try {
            feature.setStatus(false);
            featureRepository.save(feature);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-feature-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveProductImage(ProductImage productImage) {
        try {
            productImage.setStatus(true);
            productImageRepository.save(productImage);
            return true;
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE,"save-product-image-error : {0}",ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductImage(ProductImage productImage) {
        try {
            productImage.setStatus(false);
            productImageRepository.save(productImage);
            return true;
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE,"delete-product-image-error : {0}",ex.getMessage());
            return false;
        }
    }

}

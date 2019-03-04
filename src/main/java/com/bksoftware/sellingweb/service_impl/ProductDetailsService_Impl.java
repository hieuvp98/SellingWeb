package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.repository.FeatureRepository;
import com.bksoftware.sellingweb.repository.FeedbackRepository;
import com.bksoftware.sellingweb.repository.ProductDetailsRepository;
import com.bksoftware.sellingweb.service.ProductDetailsService;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductDetailsService_Impl implements ProductDetailsService {

    private final static Logger LOGGER = Logger.getLogger(ProductDetailsService_Impl.class.getName());

    private final ProductDetailsRepository productDetailsRepository;
    private final FeatureRepository featureRepository;
    private final FeedbackRepository feedbackRepository;

    public ProductDetailsService_Impl(ProductDetailsRepository productDetailsRepository, FeatureRepository featureRepository, FeedbackRepository feedbackRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.featureRepository = featureRepository;
        this.feedbackRepository = feedbackRepository;
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

}

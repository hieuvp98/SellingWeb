package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.Feature;
import com.bksoftware.sellingweb.entities.Feedback;
import com.bksoftware.sellingweb.entities.ProductDetails;

public interface ProductDetailsService {

    void saveProductDetails(ProductDetails productDetails);

    void saveFeature(Feature feature);

    void deleteFeature(Feature feature);

    void saveFeedback(Feedback feedback);

    void checkFeedback(Feedback feedback);
}

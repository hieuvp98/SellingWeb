package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.Feature;
import com.bksoftware.sellingweb.entities.Feedback;
import com.bksoftware.sellingweb.entities.ProductDetails;

public interface ProductDetailsService {

    boolean saveProductDetails(ProductDetails productDetails);

    boolean saveFeature(Feature feature);

    boolean deleteFeature(Feature feature);


}

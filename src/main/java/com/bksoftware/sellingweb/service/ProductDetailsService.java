package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.ProductDetails;

public interface ProductDetailsService {

    boolean saveProductDetails(ProductDetails productDetails);

    boolean saveFeature(Feature feature);

    boolean deleteFeature(Feature feature);


}

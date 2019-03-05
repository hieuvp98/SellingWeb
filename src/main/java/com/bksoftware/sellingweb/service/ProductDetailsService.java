package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.entities.product.ProductImage;

public interface ProductDetailsService {

    boolean saveProductDetails(ProductDetails productDetails);

    ProductDetails findById(int id);

    boolean saveFeature(Feature feature);

    boolean deleteFeature(Feature feature);

    boolean saveProductImage(ProductImage productImage);

    boolean deleteProductImage(ProductImage productImage);

}

package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.entities.product.ProductImage;

import java.util.List;

public interface ProductDetailsService {

    boolean saveProductDetails(ProductDetails productDetails);

    ProductDetails findById(int id);

    List<Feature> showFeatureById(int id);

    ProductDetails showProductDetails(int id);

    boolean saveFeature(Feature feature);

    boolean deleteFeature(Feature feature);

    boolean saveProductImage(ProductImage productImage);

    boolean deleteProductImage(ProductImage productImage);

    ProductDetails findByProduct(Product product);
}

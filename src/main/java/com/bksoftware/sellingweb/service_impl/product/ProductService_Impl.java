package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.repository.category.MediumCategoryRepository;
import com.bksoftware.sellingweb.repository.category.SmallCategoryRepository;
import com.bksoftware.sellingweb.repository.product.BuyFormRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService_Impl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());


    private final
    ProductRepository productRepository;

    @Autowired
    BuyFormRepository buyFormRepository;
    @Autowired
    private MediumCategoryRepository mediumCategoryRepository;
    @Autowired
    private SmallCategoryRepository smallCategoryRepository;

    public ProductService_Impl(ProductRepository productRepository, ProductDetailsRepository productDetailsRepository) {
        this.productRepository = productRepository;

    }


    public Map<String, Long> findGuaranteeToPhone(int phone_number) {

        Map<String, Long> mapProduct = new HashMap<>();
        try {
            List<BuyForm> buyForms = buyFormRepository.findAllByPhoneNumber(phone_number);
            List<Product> products = new ArrayList<>();
            buyForms.forEach(bf -> products.addAll(bf.getProducts()));
            products.forEach(p -> System.out.println(p.getName()));
            products.forEach(p -> {
                LocalDate date_now = LocalDate.now();
                LocalDate date_buy = p.getProductDetails().getSoldDate();
                long used_time = ChronoUnit.DAYS.between(date_buy, date_now);
                long guarantee = p.getProductDetails().getGuarantee();
                long day_lefts = guarantee - used_time;
                mapProduct.put(p.getName(), day_lefts);
            });

            return mapProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-guarantee-by-phone-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<Product> findProductByName(String name, Pageable pageable) {
        try {
            return productRepository.findByName("+" + name + "*", pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Sort sortData(String type) {
        Sort sortable = null;
        if (type.equals("ASC")) {
            sortable = Sort.by("view").ascending();
        }
        if (type.equals("DESC")) {
            sortable = Sort.by("view").descending();
        }
        return sortable;
    }


    @Override
    public Product findById(int id) {

        Product product = productRepository.findById(id);
        if (product.isStatus() == true) return product;
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            product.setStatus(false);
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public Page<Product> showProduct(int id, Pageable pageable) {

        try {
            return productRepository.showProduct(id,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductByMedium(int id, Pageable pageable) {

        try {
            return productRepository.showProductByMedium(id,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductByBig(int id, Pageable pageable) {

        try {
            return productRepository.showProductByBig(id,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }


    @Override
    public Page<Product> showProductByBigBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductByBigBranch(id,branch,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductByMediumBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductByMediumBranch(id,branch,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductBySmallBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductSmallBranch(id,branch,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-small-branch-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> findProductByPrice(int id, int low,int high,Pageable pageable) {

        try {
            return productRepository.findProductByPrice(id,low,high,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> findProductByPriceBranch(int id, int low, int high, int branch, Pageable pageable) {

        try {
            return productRepository.findProductByPriceBranch(id,low,high,branch,pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductSale(Pageable pageable) {
        try {
            return productRepository.showProductSale(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-sale-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public List<Product> findAllProductByBigCategory(BigCategory bigCategory) {
        List<MediumCategory> mediumCategories = mediumCategoryRepository.findAllByBigCategory(bigCategory);
        List<SmallCategory> smallCategories = new ArrayList<>();
        mediumCategories.forEach( mediumCategory -> smallCategories.addAll(smallCategoryRepository.findAllByMediumCategory(mediumCategory)));
        List<Product> products = new ArrayList<>();
        smallCategories.forEach( smallCategory -> products.addAll(productRepository.findAllBySmallCategory(smallCategory)));
        if (products.isEmpty())
            LOGGER.log(Level.SEVERE,"found 0 product");
        return products;
    }


}
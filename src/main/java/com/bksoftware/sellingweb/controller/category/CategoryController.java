package com.bksoftware.sellingweb.controller.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/category")
public class CategoryController {

    @Autowired
    CategoryService_Impl categoryService_imp;

    @GetMapping(value = "/showBig")
    public ResponseEntity<List<BigCategory>> showBigCategory(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<BigCategory> lstBigCategory = categoryService_imp.showBigCategory(pageable).getContent();
        return new ResponseEntity<>(lstBigCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/showBig/size")
    public ResponseEntity<Double> pageNumberBigCategory() {
        List<BigCategory> bigCategories = categoryService_imp.findAllBigCategoryPage();
        return new ResponseEntity<>(Math.ceil(bigCategories.size() / 10) + 1, HttpStatus.OK);
    }

    @GetMapping(value = "/showBig/all")
    public ResponseEntity<List<BigCategory>> allBigCategory() {
        List<BigCategory> bigCategories = categoryService_imp.findAllBigCategoryPage();
        return new ResponseEntity<>(bigCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/medium-category")
    public ResponseEntity<List<MediumCategory>> showMediumCategory(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<MediumCategory> mediumCategories = categoryService_imp.findAllMediumCategory(pageable).getContent();
        return new ResponseEntity<>(mediumCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/medium-category/size")
    public ResponseEntity<Double> pageNumberMediumCategory() {
        List<MediumCategory> lstMediumCategory = categoryService_imp.findAllMediumCategoryPage();
        return new ResponseEntity<>(Math.ceil(lstMediumCategory.size() / 10) + 1, HttpStatus.OK);
    }

    @GetMapping(value = "/medium-category/all")
    public ResponseEntity<List<MediumCategory>> allMediumCategory() {
        List<MediumCategory> lstMediumCategory = categoryService_imp.findAllMediumCategoryPage();
        return new ResponseEntity<>(lstMediumCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/small-category")
    public ResponseEntity<List<SmallCategory>> showSmallCategory(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<SmallCategory> lstSmallCategory = categoryService_imp.findAllSmallCategory(pageable).getContent();
        return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/small-category/size")
    public ResponseEntity<Double> pagesNumberSmallCategory() {
        List<SmallCategory> lstSmallCategory = categoryService_imp.findAllSmallCategoryPage();
        return new ResponseEntity<>(Math.ceil(lstSmallCategory.size() / 10) + 1, HttpStatus.OK);
    }


    @GetMapping(value = "/small-category/all")
    public ResponseEntity<List<SmallCategory>> allSmallCategory() {
        List<SmallCategory> lstSmallCategory = categoryService_imp.findAllSmallCategoryPage();
        return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/showMedium")
    public ResponseEntity<List<MediumCategory>> showMediumCategory(@RequestParam("idBigCategory") int id) {
        List<MediumCategory> lstMediumCategory = categoryService_imp.showMediumCategory(id);
        return new ResponseEntity<>(lstMediumCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/showSmall")
    public ResponseEntity<List<SmallCategory>> showSmallCategory(@RequestParam("idMediumCategory") int id) {
        List<SmallCategory> lstSmallCategory = categoryService_imp.showSmallCategory(id);
        return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/findBigCategoryById")
    public ResponseEntity<BigCategory> findBigCategoryById(@RequestParam("idBig") int id) {
        BigCategory bigCategory = categoryService_imp.findBigCategoryById(id);
        return new ResponseEntity<>(bigCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/findMediumCategoryById")
    public ResponseEntity<MediumCategory> findMediumCategoryById(@RequestParam("idMedium") int id) {
        MediumCategory mediumCategory = categoryService_imp.findMediumCategoryById(id);
        return new ResponseEntity<>(mediumCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/findSmallCategoryById")
    public ResponseEntity<SmallCategory> findSmallCategoryById(@RequestParam("idSmall") int id) {
        SmallCategory smallCategory = categoryService_imp.findSmallCategoryById(id);
        return new ResponseEntity<>(smallCategory, HttpStatus.OK);
    }
}

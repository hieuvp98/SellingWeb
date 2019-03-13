package com.bksoftware.sellingweb.controller.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<BigCategory>> showBigCategory(){
        List<BigCategory> lstBigCategory = categoryService_imp.showBigCategory();
        return new ResponseEntity<>( lstBigCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/showMedium")
    public  ResponseEntity<List<MediumCategory>> showMediumCategory(@RequestParam("idBigCategory") int id){
        List<MediumCategory> lstMediumCategory = categoryService_imp.showMediumCategory(id);
        return new ResponseEntity<>(lstMediumCategory,HttpStatus.OK);
    }

    @GetMapping(value = "/showSmall")
    public  ResponseEntity<List<SmallCategory>> showSmallCategory(@RequestParam("idMediumCategory") int id) {
        List<SmallCategory> lstSmallCategory = categoryService_imp.showSmallCategory( id);
        return new ResponseEntity<>(lstSmallCategory,HttpStatus.OK);
    }
}

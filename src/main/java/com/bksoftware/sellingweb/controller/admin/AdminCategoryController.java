package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.service_impl.CategoryService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/admin/category")
public class AdminCategoryController {
    private final CategoryService_Impl categoryService;

    public AdminCategoryController(CategoryService_Impl categoryService) {
        this.categoryService = categoryService;
    }
    // -----------------------------------Category-----------------------------------------


    //************************************************************************add


    @RolesAllowed("ADMIN")
    @PostMapping(value = "/big")
    public ResponseEntity<String> addBigCategory(@RequestBody BigCategory bigCategory) {
        bigCategory.setStatus(true);
        if (categoryService.saveBigCategory(bigCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/medium", params = "big-id")
    public ResponseEntity<String> addMediumCategory(@RequestBody MediumCategory mediumCategory,
                                                    @RequestParam(value = "big-id") int id) {
        BigCategory bigCategory = categoryService.findBigCategoryById(id);
        mediumCategory.setBigCategory(bigCategory);
        mediumCategory.setStatus(true);
        if (categoryService.saveMediumCategory(mediumCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);

    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/small", params = "medium-id")
    public ResponseEntity<String> addMediumCategory(@RequestBody SmallCategory smallCategory,
                                                    @RequestParam(value = "medium-id") int id) {
        MediumCategory mediumCategory = categoryService.findMediumCategoryById(id);
        smallCategory.setMediumCategory(mediumCategory);
        smallCategory.setStatus(true);
        if (categoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }


    //************************************************************UPDATE


    @RolesAllowed("ADMIN")
    @PutMapping(value = "/big")
    public ResponseEntity<String> updateBigCategory(@RequestBody BigCategory bigCategory) {
        if (categoryService.saveBigCategory(bigCategory))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/medium")
    public ResponseEntity<String> updateMediumCategory(@RequestBody MediumCategory mediumCategory) {
        if (categoryService.saveMediumCategory(mediumCategory))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/small")
    public ResponseEntity<String> updateSmallCategory(@RequestBody SmallCategory smallCategory) {
        if (categoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }


    //************************************************************delete


    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-big")
    public ResponseEntity<String> deleteBigCategory(@RequestBody BigCategory bigCategory) {
        if (categoryService.deleteBigCategory(bigCategory))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-medium")
    public ResponseEntity<String> deleteMediumCategory(@RequestBody MediumCategory mediumCategory) {
        if (categoryService.deleteMediumCategory(mediumCategory))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-small")
    public ResponseEntity<String> deleteSmallCategory(@RequestBody SmallCategory smallCategory) {
        if (categoryService.deleteSmallCategory(smallCategory))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

}

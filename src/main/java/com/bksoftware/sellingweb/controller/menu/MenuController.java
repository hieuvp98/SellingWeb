package com.bksoftware.sellingweb.controller.menu;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import com.bksoftware.sellingweb.service_impl.homepage.FooterMenuService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.HeaderMenuService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.HomeImageService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/menu")
public class MenuController {
    @Autowired
    private HeaderMenuService_Impl headerMenu_Imp;

    @Autowired
    private HomeImageService_Impl homeImageService_imp;

    @Autowired
    private FooterMenuService_Impl footerMenuService_imp;

    @GetMapping("/headerMenu")
    public ResponseEntity<List<HeaderMenu>> showHeaderMenu() {
        List<HeaderMenu> lstMenu = headerMenu_Imp.findAllHeaderMenu();
        return  new ResponseEntity<>(lstMenu, HttpStatus.OK);
    }

//    @GetMapping("/homeImage")
//    public ResponseEntity<HomeImage> showHomeImage() {
//        HomeImage homeImage = homeImageService_imp.getHomeImage();
//        return  new ResponseEntity<>(homeImage, HttpStatus.OK);
//    }

    @GetMapping("/footerBig")
    public ResponseEntity<List<FooterMenu>> showFooterBig(){
        List<FooterMenu> lstFooter = footerMenuService_imp.showFooterBig();
        return new ResponseEntity<>(lstFooter, HttpStatus.OK);
    }

    @GetMapping("/footerSmall")
    public ResponseEntity<List<FooterMenuDetails>> showFooterSmall(@RequestParam("idFooterBig") int idFooterBig){
        List<FooterMenuDetails> lstFooterDetail = footerMenuService_imp.showFooterDetails(idFooterBig);
        return new ResponseEntity<>(lstFooterDetail, HttpStatus.OK);
    }
}

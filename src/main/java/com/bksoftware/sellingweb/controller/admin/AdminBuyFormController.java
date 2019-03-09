package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.service_impl.SendMailService_Impl;
import com.bksoftware.sellingweb.service_impl.product.BuyFormService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping("/api/v1/admin")
public class AdminBuyFormController {
    private final BuyFormService_Impl buyFormService;
    private final ProductDetailsService_Impl productDetailsService;
    private final UserMail userMail;
    private final SendMailService_Impl sendMailService;

    public AdminBuyFormController(BuyFormService_Impl buyFormService, ProductDetailsService_Impl productDetailsService, ProductService_Impl productService, UserMail userMail, SendMailService_Impl sendMailService) {
        this.buyFormService = buyFormService;
        this.productDetailsService = productDetailsService;
        this.userMail = userMail;
        this.sendMailService = sendMailService;
    }

    @GetMapping("/uncheck-buy-form")
    public ResponseEntity<List<BuyForm>> findAllUncheckBuyForm() {
        if (buyFormService.findAllUnCheckBuyForm() != null) {
            return new ResponseEntity<>(buyFormService.findAllUnCheckBuyForm(), HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/check-buy-form",params = "buy-form-id")
    public ResponseEntity<String> checkBuyForm(@RequestParam(name = "buy-form-id")int id) {
        BuyForm buyForm =  buyFormService.findById(id);
        List<Product> products = buyForm.getProducts();
        List<ProductDetails> listProductDetails = products.stream().map(productDetailsService::findByProduct).collect(Collectors.toList());
        for (ProductDetails productDetails : listProductDetails) {
            productDetails.setSoldDate(LocalDate.now());
            if (!productDetailsService.saveProductDetails(productDetails)) {
                return new ResponseEntity<>("set sold date error", HttpStatus.BAD_REQUEST);
            }
        }
        if (!buyFormService.checkBuyForm(buyForm))
            return new ResponseEntity<>("check fail", HttpStatus.BAD_REQUEST);
        userMail.setEmailAddress(buyForm.getEmail());
        String content = "Đơn hàng của bạn đã được duyệt. Chúng tôi sẽ giao hàng trong thời gian sớm nhất";
        sendMailService.sendMail(userMail,"Thông báo đặt mua hàng",content);
        return new ResponseEntity<>("check success", HttpStatus.OK);
    }
}

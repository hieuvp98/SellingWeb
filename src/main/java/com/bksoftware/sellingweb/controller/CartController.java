package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.Cart;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public/cart")
public class CartController {

    @Autowired
    ProductService_Impl productService_imp;

    @RequestMapping(value = "/addProductCart",method = RequestMethod.GET)
    public ResponseEntity<String> addProductCart(HttpSession session, @RequestParam("productId") int productId){
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if(cartItems==null){
            cartItems = new HashMap<>();
        }
        Product product = productService_imp.findById(productId);
        if(product != null){
            if(cartItems.containsKey(productId)){
                Cart item = cartItems.get(productId);
                item.setProduct(product);
                item.setQuantity(item.getQuantity()+1);
                cartItems.put(productId,item);
            }
            else {
                Cart item = new Cart();
                item.setQuantity(1);
                item.setProduct(product);
                cartItems.put(productId,item);
            }
        }
        session.setAttribute("myCartItems",cartItems);
       // int cart =  ((HashMap<Integer, Cart>) session.getAttribute("myCartItems")).get(1).getQuantity();
       // System.out.println("quantity:"+cart);
        session.setAttribute("myCartTotal",totalPrice(cartItems));
       // System.out.println("total price:"+session.getAttribute("myCartTotal"));
        session.setAttribute("myCartNum" , cartItems.size());
        //System.out.println("QQQQQQQQQ:"+session.getAttribute("myCartNum"));
        return new ResponseEntity<>("add succsess", HttpStatus.OK);
    }
    @RequestMapping(value = "/updateProductCart",method = RequestMethod.GET)
    public ResponseEntity<String> updateProductCart(HttpSession session, @RequestParam("productId") int productId){
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if(cartItems==null){
            cartItems = new HashMap<>();
        }
        session.setAttribute("myCartItems",cartItems);
        return new ResponseEntity<>("update succsess",HttpStatus.OK);
    }

    @RequestMapping(value = "/removeProductCart", method = RequestMethod.GET)
    public ResponseEntity<String> removeProductCart( HttpSession session, @RequestParam("productId") int productId) {
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        System.out.println("total price:"+session.getAttribute("myCartTotal"));
        session.setAttribute("myCartNum", cartItems.size());
        System.out.println("QQQQQQQQQ:"+session.getAttribute("myCartNum"));

        return new ResponseEntity<>("remove succsess",HttpStatus.OK);
    }
    public double totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getSaleCost() * list.getValue().getQuantity();
        }
        return count;
    }
}


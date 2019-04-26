package com.bksoftware.sellingweb.commom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ProductCommom {

     public static final int N_GRAMS = 2;

     public static void main(String[] args) {
          System.out.println(new BCryptPasswordEncoder().encode("123456"));
     }
}

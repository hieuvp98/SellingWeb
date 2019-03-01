package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.repository.ProductRepository;
import com.bksoftware.sellingweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService_Impl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());

    @Autowired
    ProductRepository productRepository;


    public Integer findGuaranteeToPhone(int phone_number) {
        try {
            LocalDate dateBuy = productRepository.findSoldDateToPhone(phone_number);
            LocalDate date_now = LocalDate.now();
            long used_time = ChronoUnit.DAYS.between(dateBuy, date_now);
            int guarantee = productRepository.findGuaranteeToPhone(phone_number);
            return guarantee - (int) used_time;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-phone-to-guarantee-error : {0}", ex.getMessage());
        }
        return null;
    }

}

package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.Company;
import com.bksoftware.sellingweb.repository.CompanyRepository;
import com.bksoftware.sellingweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService_Impl implements CompanyService {


    //hoho

    private static final Logger LOGGER = Logger.getLogger(CompanyService_Impl.class.getName());

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompanies() {
        try {
            return  companyRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-company-error : {0}", ex.getMessage());
        }
        return null;
    }
}

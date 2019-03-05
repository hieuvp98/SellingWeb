package com.bksoftware.sellingweb.service_impl.company;

import com.bksoftware.sellingweb.entities.company.InformationCompany;
import com.bksoftware.sellingweb.repository.company.InformationCompanyRepository;
import com.bksoftware.sellingweb.service.company.InformationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class InformationCompanyService_Impl implements InformationCompanyService {


    Logger LOGGER = Logger.getLogger(InformationCompanyService_Impl.class.getName());

    @Autowired
    InformationCompanyRepository informationCompanyRepository;

    @Override
    public List<InformationCompany> findAllCompanyInformation() {
        try {
            return informationCompanyRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-information-company-error : {0}", ex.getMessage());
        }
        return null;

    }
}

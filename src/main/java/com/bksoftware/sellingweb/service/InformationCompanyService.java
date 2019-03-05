package com.bksoftware.sellingweb.service;


import com.bksoftware.sellingweb.entities.company.InformationCompany;

import java.util.List;

public interface InformationCompanyService {

    List<InformationCompany> findAllCompanyInformation();
}

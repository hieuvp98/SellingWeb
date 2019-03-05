package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.product.Partner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartnerService {

    List<Partner> findAllPartner();

    Partner findByName(String name);

    boolean savePartner(Partner partner);

    boolean deletePartner(Partner partner);

    Partner findById(int id);

}

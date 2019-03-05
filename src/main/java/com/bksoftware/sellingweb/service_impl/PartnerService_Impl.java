package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.repository.product.PartnerRepository;
import com.bksoftware.sellingweb.service.PartnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PartnerService_Impl implements PartnerService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final PartnerRepository partnerRepository;

    public PartnerService_Impl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public List<Partner> findAllPartner() {
        try {
            return partnerRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-partner-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Partner findByName(String name) {
        return partnerRepository.findByName(name);
    }

    @Override
    public boolean savePartner(Partner partner) {
        try {
            partnerRepository.save(partner);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-partner-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePartner(Partner partner) {
        try {
            partner.setStatus(false);
            partnerRepository.save(partner);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-partner-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public Partner findById(int id) {
        if (partnerRepository.findById(id).isPresent())
            return partnerRepository.findById(id).get();
        return null;
    }
}

package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner,Integer> {
    Partner findByName(String name);

}

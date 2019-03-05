package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner,Integer> {
    Partner findByName(String name);

}

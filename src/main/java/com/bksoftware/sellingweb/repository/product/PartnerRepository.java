package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    Partner findByName(String name);

    Partner findById(int id);

    @Query("select p from Partner p where p.status=true ")
    List<Partner> show();

}

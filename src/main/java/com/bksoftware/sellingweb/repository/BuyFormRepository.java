package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.BuyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyFormRepository extends JpaRepository<BuyForm,Integer> {


}

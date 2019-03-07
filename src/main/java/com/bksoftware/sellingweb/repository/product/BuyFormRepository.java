package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyFormRepository extends JpaRepository<BuyForm, Integer> {

    List<BuyForm> findAllByChecked(boolean checked);

    List<BuyForm> findAllByPhoneNumber(int phone_number);

    BuyForm findById(int id);


}

package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterMenuDetailsRepository extends JpaRepository<FooterMenuDetails,Integer> {
}

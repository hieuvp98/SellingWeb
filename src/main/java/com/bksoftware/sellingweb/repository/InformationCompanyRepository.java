package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.InformationCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationCompanyRepository extends JpaRepository<InformationCompany,Integer> {
}

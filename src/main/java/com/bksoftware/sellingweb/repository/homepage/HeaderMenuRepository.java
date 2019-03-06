package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderMenuRepository extends JpaRepository<HeaderMenu,Integer> {
}

package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface HomeImageRepository extends JpaRepository<HomeImage,Integer> {

    @Query("select hi from HomeImage hi where hi.status=true")
    HomeImage getHomeImage();
}

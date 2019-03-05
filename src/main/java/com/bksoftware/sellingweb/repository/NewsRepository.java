package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

}

package com.bksoftware.sellingweb.service.news;

import com.bksoftware.sellingweb.entities.news.News;

import java.util.List;

public interface NewsService {

    List<News> findAllNews();

    List<News> findAllNewsByViews();

    List<News> findAllNewsByTime();
}

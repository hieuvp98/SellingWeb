package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.repository.NewsRepository;
import com.bksoftware.sellingweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NewsService_Impl implements NewsService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> findAllNews() {
        try {
            return newsRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByViews() {
        try {
            List<News> newsList = newsRepository.findAll();
            newsList.sort((c1, c2) -> c2.getView() - c1.getView());
            return newsList;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-views-error : {0}", ex.getMessage());
        }
        return null;
    }
}

package com.bksoftware.sellingweb.service_impl.news;

import com.bksoftware.sellingweb.entities.news.NewsImage;
import com.bksoftware.sellingweb.repository.news.NewsImageRepository;
import com.bksoftware.sellingweb.service.news.NewsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NewsImageService_Impl implements NewsImageService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    NewsImageRepository newsImageRepository;

    @Override
    public List<NewsImage> findAllNewsImage() {
        try {
            return newsImageRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-image-error : {0}", ex.getMessage());
        }
        return null;
    }
}

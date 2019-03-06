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

    @Override
    public NewsImage findById(int id) {
        try {
            return newsImageRepository.findById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-news-image-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveNewsImage(NewsImage newsImage) {
        try {
            newsImageRepository.save(newsImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-image-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNewsImage(NewsImage newsImage) {
        try {
            newsImage.setStatus(false);
            newsImageRepository.save(newsImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-news-image-error : {0}", ex.getMessage());
        }
        return false;
    }
}

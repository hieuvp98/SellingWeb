package com.bksoftware.sellingweb.controller.news;


import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.service_impl.news.NewsService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/news")
public class NewsController {

    @Autowired
    NewsService_Impl newsService;

    @GetMapping
    public ResponseEntity<List<News>> findAllNews() {
        List<News> newsList = newsService.findAllNews();
        if (newsList != null) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/views")
    public ResponseEntity<List<News>> findAllNewsByViews() {
        List<News> newsList = newsService.findAllNewsByViews();
        if (newsList != null) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time")
    public ResponseEntity<List<News>> findAllNewsByTime(){
        List<News> newsList = newsService.findAllNewsByTime();
        if (newsList != null) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

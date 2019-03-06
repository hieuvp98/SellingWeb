package com.bksoftware.sellingweb.controller.news;


import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.service_impl.news.NewsService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/news")
public class NewsController {

    @Autowired
    NewsService_Impl newsService;

    @GetMapping
    public ResponseEntity<List<News>> findAllNews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
        Pageable pageable = PageRequest.of(page - 1, size);
        List<News> newsList = newsService.findAllNews(pageable).getContent();
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
    public ResponseEntity<List<News>> findAllNewsByTime() {
        List<News> newsList = newsService.findAllNewsByTime();
        if (newsList != null) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/topic")
    public ResponseEntity<List<News>> findAllNewsByTopic(
            @RequestParam("topic-name") String nameTopic,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<News> newsList = newsService.findAllNewsByTopic(nameTopic, pageable).getContent();
        if (newsList != null) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

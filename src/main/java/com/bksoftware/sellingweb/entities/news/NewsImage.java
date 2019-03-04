package com.bksoftware.sellingweb.entities.news;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news_image")
@SecondaryTable(name = "news")
public class NewsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "news_id")
    private News news;
}

package com.bksoftware.sellingweb.entities.news;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "news")
@SecondaryTable(name = "topic")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String content;

    private LocalDateTime time;

    private int view;

    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;
}

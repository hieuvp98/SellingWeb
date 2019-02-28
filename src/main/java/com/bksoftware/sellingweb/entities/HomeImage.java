package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "home_image")
public class HomeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private boolean status;
}

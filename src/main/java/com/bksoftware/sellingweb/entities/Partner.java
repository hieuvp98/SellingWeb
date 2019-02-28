package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    private boolean status;
}

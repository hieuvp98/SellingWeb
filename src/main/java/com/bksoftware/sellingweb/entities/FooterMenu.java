package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "footer_menu")
public class FooterMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean status;
}

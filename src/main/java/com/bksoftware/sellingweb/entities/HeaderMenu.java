package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "header_menu")
public class HeaderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean status;

}

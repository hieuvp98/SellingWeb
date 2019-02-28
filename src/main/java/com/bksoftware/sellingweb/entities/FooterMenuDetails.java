package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "footer_menu_details")
public class FooterMenuDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean status;
}

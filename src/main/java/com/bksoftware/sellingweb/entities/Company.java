package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private int hotline;
    private String email;
    private String coordinates;
    private boolean status;
}

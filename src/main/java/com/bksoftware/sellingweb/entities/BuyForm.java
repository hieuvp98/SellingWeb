package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "buy_form")
public class BuyForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "phone_number")
    private int phoneNumber;

    private String email;

    private String address;

    private String note;

    private LocalDate date;

    @OneToMany(mappedBy = "buyForm")
    Set<Product> products = new HashSet<>();

    private boolean status;
}

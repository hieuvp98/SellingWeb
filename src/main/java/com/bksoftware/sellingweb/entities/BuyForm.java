package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    private boolean status;
}

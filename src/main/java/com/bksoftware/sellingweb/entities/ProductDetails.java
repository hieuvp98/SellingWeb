package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sold_date")
    private LocalDate soldDate;

    private int guarantee;

    private boolean status;

}

package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "product_details")
@SecondaryTable(name = "product")
public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_status")
    private boolean productStatus;

    @Column(name = "sold_date")
    private LocalDate soldDate;

    private int guarantee;

    private String present;

    private boolean status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",nullable = false)
    @NotNull
    private Product product;

}

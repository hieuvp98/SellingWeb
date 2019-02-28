package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@SecondaryTables({
        @SecondaryTable(name = "buy_form"),
        @SecondaryTable(name = "partner"),
        @SecondaryTable(name = "product_details"),
        @SecondaryTable(name = "small_category")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "origin_cost")
    private int originCost;

    @Column(name = "sale_cost")
    private int saleCost;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "small_category")
    private SmallCategory smallCategory;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "product_details")
    private ProductDetails productDetails;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "buy_form" )
    private BuyForm buyForm;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "partner")
    private Partner partner;

    private boolean status;
}

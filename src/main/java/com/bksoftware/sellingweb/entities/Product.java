package com.bksoftware.sellingweb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
@SecondaryTables({
        @SecondaryTable(name = "buy_form"),
        @SecondaryTable(name = "partner"),
        @SecondaryTable(name = "product_details"),
        @SecondaryTable(name = "small_category")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Column(name = "origin_cost")
    private int originCost;

    @Column(name = "sale_cost")
    private int saleCost;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "small_category_id", nullable = false)
    @JsonIgnore
    @NonNull
    private SmallCategory smallCategory;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_details_id", nullable = false)
    @NotNull
    private ProductDetails productDetails;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "buy_form_id", nullable = false)
    private BuyForm buyForm;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id", nullable = false)
    @NotNull
    private Partner partner;

    private boolean status;

    public Product() {
    }
}

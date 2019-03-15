package com.bksoftware.sellingweb.entities.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "buy_form")
public class BuyForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "phone_number")
    @NotNull
    private int phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String address;

    private String note;

    @NotNull
    private LocalDate date;

    @NotNull
    private boolean checked;

    @NotNull
    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "buy_form_has_product",
            joinColumns = @JoinColumn(name = "buy_form_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @NotNull
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}

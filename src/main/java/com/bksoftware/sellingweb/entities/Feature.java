package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "feature")
@SecondaryTables({
        @SecondaryTable(name = "product_details")
})
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String detail;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(table = "product_details")
    private Feature feature;

    private boolean status;
}

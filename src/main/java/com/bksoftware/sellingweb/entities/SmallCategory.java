package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "small_category")
@SecondaryTables({
        @SecondaryTable(name = "medium_category")
})
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(table = "medium_category")
    private MediumCategory mediumCategory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "smallCategory")
    private List<Product> products;
    private boolean status;
}

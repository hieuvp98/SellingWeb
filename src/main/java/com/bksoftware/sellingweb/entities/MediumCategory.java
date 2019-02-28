package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "medium_category")
@SecondaryTables({
        @SecondaryTable(name = "big_category")
})
public class MediumCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "big_category")
    private BigCategory bigCategory;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "mediumCategory")
    private Set<SmallCategory> smallCategories = new HashSet<>();
    private boolean status;
}

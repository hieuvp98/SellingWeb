package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "small_category")
@SecondaryTables({
        @SecondaryTable(name = "medium_category")
})
public class SmallCategory implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "medium_category_id",nullable = false)
    private MediumCategory mediumCategory;

    private boolean status;
}

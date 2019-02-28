package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "big_category")
public class BigCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(mappedBy = "bigCategory")
    private Set<MediumCategory> mediumCategories;
    private boolean status;

}

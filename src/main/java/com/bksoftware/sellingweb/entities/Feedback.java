package com.bksoftware.sellingweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedback")
@SecondaryTables({
        @SecondaryTable(name = "product_details")
})
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String text;
    private int evaluate;
    private LocalDateTime time;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(table = "product_details")
    private ProductDetails productDetails;

    private boolean status;

}

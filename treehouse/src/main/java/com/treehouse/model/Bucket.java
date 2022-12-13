package com.treehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bucketId;
    private Integer totalPrice;
    private Integer plantPrice;
    private Integer planterPrice;
    private Integer seedsPrice;
    private Integer plantQuantity;
    private Integer planterQuantity;
    private Integer seedsQuantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Planter> planters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Plant> plants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seeds> seeds = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Order orders;


}

package com.treehouse.model;

import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
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
    private Integer totalItems;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Planter> planters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Plant> plants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seeds> seeds = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Order orders;

}

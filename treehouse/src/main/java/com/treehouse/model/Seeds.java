package com.treehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seeds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seedsId;
    private String commonName;
    private String bloomTime;
    private String watering;
    private String difficultyLevel;
    private String temperature;
    private String typeOfSeeds;
    private String SeedsDescription;
    private Integer SeedsStock;
    private Integer SeedsCost;
    private Integer SeedsPerPacket;
    private Integer SeedsQuantity;
    private String seedsImage;

}

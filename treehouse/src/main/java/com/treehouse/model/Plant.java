package com.treehouse.model;

import lombok.*;

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
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer plantId;
    private Integer plantHeight;
    private String plantSpread;
    private String commonName;
    private String bloomTime;
    private String medicinalOrCulinaryUse;
    private String difficultyLevel;
    private String temperature;
    private String typeOfPlant;
    private String plantsStock;
    private Integer plantCost;
    private Integer plantQuantity;
    private String plantImage;


}

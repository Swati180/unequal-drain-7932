package com.treehouse.model.DTO;

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
public class SeedsDto {

        @Id
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
        private String  seedsImage;

    }

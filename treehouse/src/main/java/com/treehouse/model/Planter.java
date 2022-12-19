package com.treehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Planter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer PlanterId;
    private Integer PlanterHeight;
    private Integer PlanterCapacity;
    private Integer DrainHoles;
    private String PlanterColor;
    private String  PlanterShape;
    private Integer PlanterStock;
    private Integer PlanterCost;
    private Integer PlanterQuantity;
    private String planterImage;

}

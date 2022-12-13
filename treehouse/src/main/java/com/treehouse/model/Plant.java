package com.treehouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer plantId;

}

package com.treehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerLogin {
    @Id
    private Integer customerId;

    @Column(unique = true)
    private String key;
    private LocalDate localDate;


}

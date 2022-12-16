package com.treehouse.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    
    private String adminName;

   @Email(message = "Please provide valid email")
    private String adminEmail;
   
   @Size(min=4,max=6,message = "Please provide character between 4-6")
    private String adminPassword;






}

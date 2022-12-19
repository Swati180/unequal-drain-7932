package com.treehouse.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String firstname;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Please provide valid email")
    private String email;
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})",message="atLeast contain 1 Numeric,1 special character or one lowercase, One upper character and size must be 6 to 12")
    private String password;
    @Column(unique = true)
    @Size(min = 10,max = 12,message = "please Enter valid Mobile Number")
    private String mobile;
    @OneToOne(cascade = CascadeType.ALL)
    private Bucket bucket;

}

package com.treehouse.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

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



}

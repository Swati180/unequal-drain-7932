package com.treehouse.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String mobile;



}

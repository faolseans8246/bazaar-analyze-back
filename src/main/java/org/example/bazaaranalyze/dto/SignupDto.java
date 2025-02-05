package org.example.bazaaranalyze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaaranalyze.roles.UserRoles;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String username;
    private String password;
    private String conf_pass;
    private String email;
    private String phone;
    private UserRoles role;
}

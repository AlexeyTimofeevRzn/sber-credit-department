package com.example.sbercreditdepartment.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO extends GenericDTO {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String phone;
    private String address;
    private String email;
    private int roleId;
    private List<Integer> contracts;

    public UserDTO(int id, String login, String email, String firstName, String lastName, List<Integer> contracts) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdWhen = LocalDateTime.now();
        this.contracts = contracts;
    }
}

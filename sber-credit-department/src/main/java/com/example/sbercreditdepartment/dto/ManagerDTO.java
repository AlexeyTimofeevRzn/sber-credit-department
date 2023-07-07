package com.example.sbercreditdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO extends GenericDTO {

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

}

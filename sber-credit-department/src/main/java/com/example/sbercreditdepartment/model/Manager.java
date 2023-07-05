package com.example.sbercreditdepartment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Table(name = "managers")
@Entity
@Getter
@Setter
public class Manager extends GenericModel {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "FK_USERS_ROLE"))
    private Role role;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<CreditContract> contracts;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Request> requests;
}

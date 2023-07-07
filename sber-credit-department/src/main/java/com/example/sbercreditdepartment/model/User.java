package com.example.sbercreditdepartment.model;

import com.example.sbercreditdepartment.utils.constants.UserRolesConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends GenericModel {

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CreditContract> contracts;

    public User(int id, String login, String email, String firstName, String lastName, List<CreditContract> contracts) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdWhen = LocalDateTime.now();
        this.contracts = contracts;
    }

}

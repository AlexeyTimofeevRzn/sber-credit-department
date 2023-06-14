package com.example.sbercreditdepartment.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name = "requests")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_REQUESTS_USER"))
    private User user;

    @Column(name = "date_of_request")
    private Date dateOfRequest;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "required_debt")
    private Integer requiredDebt;

    @ManyToOne
    @JoinColumn(name = "credit_type", nullable = false,
    foreignKey = @ForeignKey(name = "FK_REQUESTS_CREDIT_TYPE"))
    private Credit credit;

    @Column(name = "is_accepted", columnDefinition = "boolean default false")
    private boolean isAccepted;

}

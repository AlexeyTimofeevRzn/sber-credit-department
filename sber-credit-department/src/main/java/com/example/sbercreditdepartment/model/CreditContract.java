package com.example.sbercreditdepartment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "credit_contracts")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditContract extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "credit_type", nullable = false,
            foreignKey = @ForeignKey(name = "FK_CREDIT_CONTRACTS_CREDIT_TYPE"))
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_CREDIT_CONTRACTS_USER"))
    private User user;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "current_debt")
    private Double currentDebt;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_CREDIT_CONTRACTS_MANAGER"))
    private User manager;

}

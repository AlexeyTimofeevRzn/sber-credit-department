package com.example.sbercreditdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditContractDTO extends GenericDTO {

    private int credit;

    private int user;

    private Date startDate;

    private int duration;

    private double monthlyPayment;

    private double currentDebt;

    private int manager;

}

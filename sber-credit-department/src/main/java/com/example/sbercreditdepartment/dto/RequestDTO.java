package com.example.sbercreditdepartment.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RequestDTO {

    private LocalDateTime dateOfRequest = LocalDateTime.now();

    private int duration;

    private double monthlyPayment;

    private int requiredDebt;

    private int credit;

    private boolean isAccepted = false;

}

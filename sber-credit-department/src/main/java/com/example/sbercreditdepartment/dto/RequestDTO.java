package com.example.sbercreditdepartment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RequestDTO extends GenericDTO {

    private int user;
    private int manager;
    private LocalDateTime dateOfRequest = LocalDateTime.now();
    private int duration;
    private double monthlyPayment;
    private int maximumDebt;
    private String status;

    private int credit;

    private boolean isAccepted = false;

}

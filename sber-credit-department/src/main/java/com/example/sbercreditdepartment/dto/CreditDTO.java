package com.example.sbercreditdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO extends GenericDTO {

    private String name;

    private String description;

    private Double percentage;

    private Integer minimalSum;

    private Integer maximumSum;

    private Integer maxDuration;

    private boolean isWithDeposit;

}

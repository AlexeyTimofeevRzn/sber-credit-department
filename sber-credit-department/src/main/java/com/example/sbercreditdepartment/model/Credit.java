package com.example.sbercreditdepartment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credit_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credit extends GenericModel {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "minimal_sum")
    private Integer minimalSum;

    @Column(name = "maximum_sum")
    private Integer maximumSum;

    @Column(name = "max_duration")
    private Integer maxDuration;

    @Column(name = "is_with_deposit")
    private boolean isWithDeposit;

    @Column(name = "is_deleted")
    private boolean isDeleted;

}

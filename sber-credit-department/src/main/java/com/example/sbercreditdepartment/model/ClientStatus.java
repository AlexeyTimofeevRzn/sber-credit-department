package com.example.sbercreditdepartment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "client_status")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientStatus extends GenericModel {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "income_to_get", nullable = false)
    private Integer incomeToGet;

}

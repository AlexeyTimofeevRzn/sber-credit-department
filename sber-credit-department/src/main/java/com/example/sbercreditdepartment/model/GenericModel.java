package com.example.sbercreditdepartment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GenericModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "default_generator")
    protected Integer id;

    @Column(name = "created_when")
    protected LocalDateTime createdWhen;

    @Column(name = "created_by")
    protected String createdBy;

}
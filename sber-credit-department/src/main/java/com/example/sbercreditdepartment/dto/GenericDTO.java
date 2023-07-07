package com.example.sbercreditdepartment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GenericDTO {

    protected Integer id;

    protected String createdBy = "Admin";

    protected LocalDateTime createdWhen = LocalDateTime.now();

}

package com.example.sbercreditdepartment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GenericDTO {

    private Integer id;

    private String createdBy = "Admin";

    private LocalDateTime createdWhen = LocalDateTime.now();

}

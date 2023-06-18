package com.example.sbercreditdepartment.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {

    DECLINED("Declined"), CONSIDERED("Considered"), ACCEPTED("Accepted");

    private String value;

    RequestStatus(String value) {
        this.value = value;
    }

}

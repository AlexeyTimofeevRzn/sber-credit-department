package com.example.sbercreditdepartment.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {

    DECLINED("Declined"), CONSIDERED("Considered"), ACCEPTED("Accepted"), SIGNED("Signed");

    private String value;

    RequestStatus(String value) {
        this.value = value;
    }

}

package com.example.sbercreditdepartment.exception;

import com.example.sbercreditdepartment.utils.constants.ExceptionConstants;
import javassist.NotFoundException;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException() {
        super(ExceptionConstants.REQUEST_NOT_FOUND);
    }
}

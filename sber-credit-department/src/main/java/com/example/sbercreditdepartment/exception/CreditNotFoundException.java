package com.example.sbercreditdepartment.exception;

import com.example.sbercreditdepartment.utils.constants.ExceptionConstants;
import org.webjars.NotFoundException;

public class CreditNotFoundException extends NotFoundException {
    public CreditNotFoundException() {
        super(ExceptionConstants.CREDIT_NOT_FOUND);
    }
}

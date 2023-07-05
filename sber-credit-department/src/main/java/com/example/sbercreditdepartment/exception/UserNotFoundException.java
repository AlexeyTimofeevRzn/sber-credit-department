package com.example.sbercreditdepartment.exception;


import com.example.sbercreditdepartment.utils.constants.ExceptionConstants;
import org.webjars.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(ExceptionConstants.USER_NOT_FOUND);
    }
}

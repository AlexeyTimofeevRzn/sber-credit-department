package com.example.sbercreditdepartment.exception;

import org.webjars.NotFoundException;

public class AuthenticationException extends NotFoundException {

    public AuthenticationException() {
        super("Ошибка аутентификации");
    }
}

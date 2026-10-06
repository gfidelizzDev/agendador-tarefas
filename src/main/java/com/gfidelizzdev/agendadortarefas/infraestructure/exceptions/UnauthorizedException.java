package com.gfidelizzdev.agendadortarefas.infraestructure.exceptions;

import javax.security.sasl.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}

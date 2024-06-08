package com.study.spring.base.shared.exceptions;

import java.io.Serial;

@SuppressWarnings("unused")
public class EntityNotFoundException extends BusinessException{

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super("EntityNotFound");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}

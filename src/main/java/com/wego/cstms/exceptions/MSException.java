package com.wego.cstms.exceptions;

import org.springframework.stereotype.Component;

@Component
public class MSException extends Exception{

    private final ExceptionMessageBuilder exceptionMessageBuilder;

    public MSException(ExceptionMessageBuilder exceptionMessageBuilder) {
        this.exceptionMessageBuilder = exceptionMessageBuilder;
    }

    public RuntimeException EntityNotFoundException(EntityType entityType, Integer id){
        String exceptionMessage = exceptionMessageBuilder.getExceptionMessage(entityType,ExceptionType.ENTITY_NOT_FOUND,id);
        throw new RuntimeException(exceptionMessage);
    }

    public RuntimeException EntityAlreadyExistsException(EntityType entityType, String username){
        String exceptionMessage = exceptionMessageBuilder.getExceptionMessage(entityType,ExceptionType.DUPLICATE_ENTITY,username);
        throw new RuntimeException(exceptionMessage);
    }


}

package com.wego.cstms.exceptions;

import com.wego.cstms.config.PropertiesConfig;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ExceptionMessageBuilder {

    private final PropertiesConfig propertiesConfig;

    public ExceptionMessageBuilder(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }


    public String getExceptionMessage(EntityType entityType, ExceptionType exceptionType, Object id){
        String messageTemplate = propertiesConfig.getConfigValue(exceptionType.value);
        return MessageFormat.format(messageTemplate, entityType.value,id);

    }
}

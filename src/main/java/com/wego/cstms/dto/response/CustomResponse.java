package com.wego.cstms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class CustomResponse {
    private final Object payLoad;
    private final String errorMessage;
    private final Object meta;

}

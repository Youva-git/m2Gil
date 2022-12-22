package com.fullstack.backend.handlers;

import com.fullstack.backend.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;
    private ErrorCodes errorCodes;
    private String msgErr;
    private List<String> errors = new ArrayList<>();
}

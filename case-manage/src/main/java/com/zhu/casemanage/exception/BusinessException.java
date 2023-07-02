package com.zhu.casemanage.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String message;

    public BusinessException(String message) {
        this.code = 500;
        this.message = message;
    }
}

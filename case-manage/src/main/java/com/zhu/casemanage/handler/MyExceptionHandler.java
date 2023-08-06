package com.zhu.casemanage.handler;

import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(annotations = RestController.class)
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public Result businessException(BusinessException e){
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        return Result.failed(e.getMessage());
    }

}


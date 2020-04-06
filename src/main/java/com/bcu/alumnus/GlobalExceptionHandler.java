package com.bcu.alumnus;

import com.bcu.alumnus.entity.Message;
import com.bcu.alumnus.exception.InvalidTokenException;
import com.bcu.alumnus.exception.PermissionDeniedExpectation;
import com.bcu.alumnus.exception.TokenNotExistsExpectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
* @Author: Wls
* @Date: 10:54 2020/4/6
* @Description: 全局异常处理器
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Message InvalidTokenExceptionHandler(InvalidTokenException ex){
        return Message.fail(ex.getMessage());
    }

    @ExceptionHandler(PermissionDeniedExpectation.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Message PermissionDeniedExceptionHandler(PermissionDeniedExpectation ex){
        return Message.fail(ex.getMessage());
    }

    @ExceptionHandler(TokenNotExistsExpectation.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Message PermissionDeniedExceptionHandler(TokenNotExistsExpectation ex){
        return Message.fail(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message handle(ValidationException exception) {
        String errorInfo = "";
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();

            for (ConstraintViolation<?> item : violations) {
                errorInfo = errorInfo + "[" + item.getMessage() + "]";
            }
        }
        return Message.fail(errorInfo);
    }




}

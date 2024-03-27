package com.jfteam.sharedrawing.advice;

import com.jfteam.sharedrawing.dto.error.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import com.jfteam.sharedrawing.exception.IllegalEntityException;
import com.jfteam.sharedrawing.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class AbcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<ErrorDto> handleException(Exception ex) {
        HttpStatus status = findHttpStatus(ex);
        ex.printStackTrace();
        ErrorDto e = new ErrorDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(e);
    }

    public static HttpStatus findHttpStatus(Exception ex) {
        HttpStatus status;

        if (ex instanceof NoSuchEntityException || ex instanceof NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
        }
        else if (ex instanceof HttpMessageNotReadableException ||
                ex instanceof HttpRequestMethodNotSupportedException ||
                ex instanceof MissingServletRequestParameterException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof IllegalEntityException ||
                ex instanceof ConstraintViolationException) {
            status = HttpStatus.BAD_REQUEST;

        } else if (ex instanceof ExpiredJwtException || ex instanceof AuthenticationException) {
            status = HttpStatus.UNAUTHORIZED;
        }
        else if (ex instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        }
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return status;
    }

}

package com.jfteam.sharedrawing.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfteam.sharedrawing.advice.AbcExceptionHandler;
import com.jfteam.sharedrawing.config.AppConstants;
import com.jfteam.sharedrawing.dto.comment.CommentDto;
import com.jfteam.sharedrawing.dto.error.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


public abstract class AbstractController implements AuthenticationEntryPoint {
    protected final String AUTH_PATH = AppConstants.AUTH_PATH;
    protected final String PUBLIC_PATH = AppConstants.PUBLIC_PATH;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        int httpCode = AbcExceptionHandler.findHttpStatus(authException).value();
        response.setStatus(httpCode);
        String json = new ObjectMapper().writeValueAsString(new ErrorDto(httpCode, authException.getMessage()));
        response.getWriter().write(json);
        response.flushBuffer();
    }

    public static <Entity, Dto> Dto mapEntityToDto(Entity entity, Class<Dto> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}

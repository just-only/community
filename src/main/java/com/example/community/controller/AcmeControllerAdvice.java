package com.example.community.controller;

import com.example.community.exception.MyException;
import com.example.community.exception.MyExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2018-2020
 * FileName: AcmeControllerAdvice
 * Author:   wdy
 * Date:     2020/4/17 9:19
 * Description: 有关异常页的处理
 */
@ControllerAdvice
public class AcmeControllerAdvice{

    @ExceptionHandler(MyException.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        HttpStatus status = getStatus(request);
        if(status!=HttpStatus.INTERNAL_SERVER_ERROR) {
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message", "众里寻它千百度，它也不在文件最深处！");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}

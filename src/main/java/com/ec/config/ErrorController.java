package com.ec.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
@ControllerAdvice
public class ErrorController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL:{}, Exception:{}",request.getRequestURL(),e.getMessage());
        //如果有自定义异常则交给spring处理
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
           throw e;
        }
        ModelAndView m=new ModelAndView();
        m.addObject("url",request.getRequestURL());
        m.addObject("exception",e);
        m.setViewName("error/error");
        return m;
    }

}

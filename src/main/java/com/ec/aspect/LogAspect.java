package com.ec.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志处理
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.ec.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        //获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获取类名+方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request:{}", requestLog);

        logger.info("----before----");
    }

    @After("log()")
    public void after() {
        logger.info("----after----");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void afterReturn(Object result) {
        logger.info("Result:{}", result);
    }

    /*日志需要记录的内容类*/
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;//哪个类的哪个方法
        private Object[] args; //请求参数

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog[" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ']';
        }
    }

}

package com.example.springboot.aop;

import com.example.springboot.controller.CustomerController;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class Aop {
    Logger log = LoggerFactory.getLogger(Aop.class);
//    Logger log = LogManager.getLogger(Aop.class);
    @Around("execution(* com.example.springboot.controller..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Around start");
        Date start = new Date();
        Object obj = pjp.proceed();
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        log.info("執行了: " + time +" ms");
        log.info("Around end");
        return obj;
    }
}

package com.suhas.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final String DELIMITER = ".";

    @Before("execution(* com.suhas.service.*.*(..)) || execution(* com.suhas.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        StringBuffer logMessage = new StringBuffer("Entered ");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(DELIMITER);
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }
        logMessage.append(")");
        logger.info(logMessage.toString());
    }

    @After("execution(* com.suhas.service.*.*(..)) || execution(* com.suhas.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        StringBuffer logMessage = new StringBuffer("Exiting ");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(DELIMITER);
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }
        logMessage.append(")");
        logger.info(logMessage.toString());
    }

    @AfterReturning(value = "execution(* com.suhas.service.*.*(..)) || execution(* com.suhas.controller.*.*(..))",
            returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        logger.info("{} returned with value {}", joinPoint, retVal);
    }


}

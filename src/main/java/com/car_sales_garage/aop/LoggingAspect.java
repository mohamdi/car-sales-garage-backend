package com.car_sales_garage.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* com.car_sales_garage.controller.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("Method called: {}", joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            log.info("Args: {}", Arrays.toString(joinPoint.getArgs()));
        }
        if (result != null) {
            log.info("Result: {}", result);
        }
        log.info("Execution time: {} ms", (currentTimeMillis() - startTime));
        return result;
    }

}

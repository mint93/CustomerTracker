package com.practice.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.practice.springdemo.controller.*.*(..))")
	private void forControllerPackage() {} 
	
	@Pointcut("execution(* com.practice.springdemo.service.*.*(..))")
	private void forServicePackage() {} 
	
	@Pointcut("execution(* com.practice.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("logger: in @Before: calling method: " + method);
		Object[] args = joinPoint.getArgs();
		for(Object arg : args) {
			logger.info("with argument: " + arg);
		}
	}
	
	@AfterReturning(pointcut="forAppFlow()",
					returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("logger: in @AfterReturning: calling method: " + method);
		logger.info("Result is: " + result);
	}
}








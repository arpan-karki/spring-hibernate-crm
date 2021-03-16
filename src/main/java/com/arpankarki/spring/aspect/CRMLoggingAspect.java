package com.arpankarki.spring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut(value = "execution(* com.arpankarki.spring.controllers.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut(value = "execution(* com.arpankarki.spring.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut(value = "execution(* com.arpankarki.spring.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
	}

	@Before(value = "forAppFlow()")
	public void before(JoinPoint joinPoint) {
		logger.info("===> in @Before: calling method: " + joinPoint.getSignature().toShortString());
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object []args = joinPoint.getArgs();
		for(Object arg : args) {
			logger.info("===> in @Before: calling method: " + joinPoint.getSignature().toShortString());
			logger.info("===> in @Before: Arguments passed: " + arg);
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()",returning = "result")
	public void afterReturning(JoinPoint joinPoint,Object result) {
		logger.info("===> in @AfterReturning: calling method: " + joinPoint.getSignature().toShortString());
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		logger.info("===> Returned: " + result);
	}
}

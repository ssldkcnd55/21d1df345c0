package com.hg.second.test.service.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	//Pointcut 만 따로 모아서 설정할 수도 있음
	
	@Pointcut("execution(* com.hg.second..service.*Impl.*(..))")
	public void serviceAllPointcut() {}
	
	@Pointcut("execution(* com.hg.second..service.*Impl.select*(..))")
	public void getPointcut() {}
	
	@Pointcut("execution(* com.hg.second..service.*Impl.insert*(..))")
	public void setPointcut() {}
}
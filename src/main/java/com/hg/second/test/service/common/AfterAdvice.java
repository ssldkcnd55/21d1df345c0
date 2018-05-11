package com.hg.second.test.service.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* com.hg.second..service.*Impl.*(..))")
	public void allPointcut() {}
	
	@After("allPointcut()")
	public void finallyLog() {
		//예외 발생 여부에 상관없이 무조건 수행되는 어드바이스임
		System.out.println("[후 실행] 비즈니스 로직 수행 후 무조건 작동");
	}
}

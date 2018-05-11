package com.hg.second.test.service.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	//로그 출력용 메소드를 이용해서 aop 테스트해 봄
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.hg.second..*.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
		//포인트컷으로 지정된 대상 메소드가 실행되기 전 공통으로
		//작동될 메소드가 됨
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		System.out.println("dddd");
		
		logger.info("[메소드 호출전 확인] : " + methodName + 
				"() 메소드 매개변수 갯수 - " + args.length);
		
		for(int i = 0; i < args.length; i++)
			System.out.println(i + "번째 매개변수 정보 : " + 
					args[i].toString());
	}

}










package com.hg.second.test.service.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdviceOutAspect {
	//따로 작성해 놓은 포인트컷을 사용하기 위한 어드바이스임.
	
	@Before("PointcutCommon.serviceAllPointcut()")
	public void beforeConsole(JoinPoint jp) {
		//모든 서비스 구현 클래스의 모든 메소드 구동 직전에 작동됨
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("[사전처리] : " + methodName + 
				"() 메소드 첫번째 전달인자 정보 : " 
				+ ((args.length > 0)? args[0].toString(): 0));
	}
}
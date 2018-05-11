package com.hg.second.test.service.common;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.hg.second.member.model.vo.Member;

@Service
@Aspect
public class AfterReturningAdvice {	

	/*@Pointcut("execution(* com.kh.testString.service..*Impl.get*(..))")
	public void getPointcut() {}*/
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		//비즈니스 메소드가 리턴한 결과 데이터를 다른 용도로 처리할 때 사용한다.
		String methodName = jp.getSignature().getName();
		
		if(returnObj instanceof com.hg.second.member.model.vo.Member) {
			Member member = (Member)returnObj;
			if(member.getUserid().equals("user01")) {
				System.out.println("로그인 : user01");
			}
		}
		
		System.out.println("[메소드 리턴] " + methodName + 
				"() 메소드 리턴값 : " + returnObj.toString());
	}
}

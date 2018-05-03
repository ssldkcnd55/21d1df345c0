package com.hg.second.member.model.service;

import java.util.ArrayList;


import com.hg.second.member.model.vo.Member;

public interface MemberService {
	//추상 메소드만 멤버로 가질수 있는 추상클래스의 변형체
	//상숲필드도 멤버로 가질수 있음
	/*public static final int MID=1; - 상수필드*/
	
	/*public abstract */Member selectMember(Member member);
	Member myInfo(String userid);
	ArrayList<Member> selectAll();
	Member selectOne(String userid);
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
	
	
}

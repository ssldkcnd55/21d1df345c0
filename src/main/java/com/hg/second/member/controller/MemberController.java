package com.hg.second.member.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.hg.second.member.model.service.MemberService;
import com.hg.second.member.model.vo.Member;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	/* @ModelAttribute("loginUser") */
	public /* ModelAndView */ String loginCheck(Member member,
			/* ModelAndView mv, */Model model/* HttpSession session */, SessionStatus status) {
		
		logger.info("loginCheck() run : " + member);
		System.out.println("전송와서 저장된 값 : " + member);
		// 스프링에서는 메소드의 매개변수로 클래스명 레퍼런스 변수 선언하면 자동으로 해당클래스에 대한 객체 생성이 된다
		Member loginUser = memberService.selectMember(member);

		try {
			boolean check = pwdEncoder.matches(member.getUserpwd(), loginUser.getUserpwd());

			if (check == true) {
				model.addAttribute("loginUser", loginUser);
				status.setComplete();
				System.out.println("login pwdEncoder true");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return "home";
		}
		/* System.out.println("loginUser : " + loginUser); */
		// session로 할때
		/*
		 * session.setAttribute("loginUser", loginUser); status.setComplete();
		 */

		// model로 할때
		/*
		 * model.addAttribute("loginUser", loginUser); status.setComplete();
		 */

		// mv로 할때
		/*
		 * mv.addObject("loginUser", loginUser); mv.setViewName("home");
		 */

	}

	/*
	 * @RequestMapping("test.do") public String testMethod(HttpServletRequest
	 * request, HttpServletResponse response) public String testMethod(
	 * 
	 * @RequestParam(value="userid") String userid,
	 * 
	 * @RequestParam(value="userpwd") String userpwd){ String userid =
	 * request.getParameter("userid"); String userpwd =
	 * request.getParameter("userpwd");
	 * 
	 * Member member = new Member(); member.setUserid(userid);
	 * member.setUserpwd(userpwd);
	 * 
	 * System.out.println("member : " + member); return "home"; }
	 */

	@RequestMapping("moveEnroll.do")
	public String moveEnrollPage() {
		return "member/enroll";
	}

	@RequestMapping("enroll.do")
	public String enroll(Member member) {

		System.out.println("enroll dao : " + member);
		memberService.insertMember(member);
		return "home";
	}

	@RequestMapping("myinfo.do")
	public ModelAndView myInfo(ModelAndView mv, String userid) {

		Member userId = memberService.myInfo(userid);

		mv.addObject("myinfo", userId);
		mv.setViewName("member/myinfo");
		return mv;
	}

	@RequestMapping("logout.do")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "home";
	}

	@RequestMapping("testView.do")
	public String moveTestView() {
		return "test/testCrypto";
	}

	@RequestMapping(value = "bcryp.do", method = RequestMethod.POST)
	public String testBcryptoPassword(Member member) {
		System.out.println("암호  : " + member.getUserpwd());
		System.out.println("암호화된 패스워드 : " + pwdEncoder.encode(member.getUserpwd()));

		member.setUserpwd(pwdEncoder.encode(member.getUserpwd()));

		System.out.println(member.getUserpwd().matches("$2a$10$V7Z3FVZ.JkX.UydnT1h36e4cF5p4rQVrkoRIPRLgaAONMn2Flz6.q"));

		return "test/testCrypto";
	}

}

package com.hg.second.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hg.second.test.model.vo.Sample;
import com.hg.second.test.model.vo.User;

@Controller
public class TestController {
	//로그 처리용 객체 의존성 주입(종속객체 주입) 처리
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@RequestMapping(value="moveAjax.do", method=RequestMethod.GET)
	public String moveAjaxPage() {
		return "test/testAjaxPage";
	}
	
	@RequestMapping(value="test1.do", method=RequestMethod.POST)
	public void test1Method(Sample command, 
			HttpServletResponse response) throws IOException {
		logger.info("test1.do method run...");
		
		System.out.println("command : " + command);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(command.getName().equals("신사임당")) {
			out.append("ok");
			out.flush();
		}else {
			out.append("fail");
			out.flush();
		}
		
		out.close();
	}
	
	@RequestMapping(value="test2.do", method=RequestMethod.POST,  produces = "application/json; charset=utf8")
	@ResponseBody   //결과를 response 객체에 담아서 보내라는 뜻의 어노테이션임
	public String test2Method(HttpServletResponse response) 
			throws IOException{
		logger.info("test2.do method run...");
	
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", "홍길동");
		job.put("content","json 객체를 뷰로 리턴하는 테스트 글입니다.");
		
		return job.toJSONString();
	}
	
	
	@RequestMapping(value="test3.do", method=RequestMethod.POST, produces = "application/json; application/text; application/xml; charset=utf8")
	   @ResponseBody
	public void test3Method(HttpServletResponse response) throws IOException {
		logger.info("test3.do method run....");
		//List를 json 배열로 만들어서 요청한 뷰로 전송 처리함
		response.setContentType("application/json; charset=utf-8");
		List<User> list = new ArrayList<User>();
		list.add(new User("u1234", "p1234", "홍길동", 25, "h1234@iei.org", "010-1234-7777"));
		list.add(new User("u2345", "p2345", "박문수", 33, "p2345@iei.org", "010-2345-7777"));
		list.add(new User("u3456", "p3456", "장영실", 45, "j3456@iei.org", "010-3456-7777"));
		list.add(new User("u4567", "p4567", "이순신", 52, "l4567@iei.org", "010-4567-7777"));
		list.add(new User("u5678", "p5678", "유관순", 16, "y5678@iei.org", "010-5678-7777"));
		
		//json 배열 객체 생성
		JSONArray jarr = new JSONArray();
		
		//list 를 jarr 로 복사하기
		for(User user : list) {
			//추출한 user를 json 객체에 담기
			JSONObject juser = new JSONObject();
			juser.put("userid", user.getUserid());
			juser.put("userpwd", user.getUserpwd());
			juser.put("username",user.getUsername());
			juser.put("age", user.getAge());
			juser.put("email", user.getEmail());
			juser.put("phone", user.getPhone());
			
			//json 배열에 json 객체 저장
			jarr.add(juser);
		}
		
		//전송용 최종 json 객체 선언
		JSONObject sendJson = new JSONObject();
		sendJson.put("list", jarr);
		
		PrintWriter out = response.getWriter();
		out.print(sendJson.toJSONString());
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="test4.do", method=RequestMethod.POST)
	public ModelAndView test4Method(ModelAndView mv , HttpServletResponse response) 
			throws UnsupportedEncodingException {
		logger.info("test4.do method run...");
		response.setContentType("application/json; charset=utf-8");

		//map 객체를 ModelAndView 에 담아서 JsonView 로 보냄
		Sample samp = new Sample("이순신", 55);
		samp.setName(samp.getName());
		
		Map<String, Sample> map = new HashMap<String, Sample>();
		map.put("samp", samp);
		
		mv.addAllObjects(map);
		//뷰지정 : JsonView 를 bean 으로 등록하고, id를 뷰이름으로 지정함
		mv.setViewName("jsonView");
		
		return mv;  //뷰리졸버로 전달되고, 요청한 ajax는 json 객체를 받음
	}
	
	
	
}

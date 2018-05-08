package com.hg.second.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.second.test.model.vo.Sample;

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
	
}

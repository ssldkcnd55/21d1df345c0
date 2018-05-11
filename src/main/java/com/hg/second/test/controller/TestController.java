package com.hg.second.test.controller;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hg.second.test.model.vo.Sample;
import com.hg.second.test.model.vo.User;

@Controller
public class TestController {
	// 로그 처리용 객체 의존성 주입(종속객체 주입) 처리
	/*private static final Logger logger = LoggerFactory.getLogger(TestController.class);*/
	
	@RequestMapping("testAOP.do")
	public String moveAOPPage() {
		
		return "test/testAOPPage";
	}

	@RequestMapping(value = "moveFileup.do", method = RequestMethod.GET)
	public String moveFileUploadPage() {
		return "test/testFileUpload";
	}

	@RequestMapping(value="tinsert.do", method=RequestMethod.POST)
	public String testFileUpload(Sample sample, 
			HttpServletRequest request, 
			@RequestParam(name="file", required=false) MultipartFile file) {
		
		//System.out.println("sample : " + sample);
		//System.out.println("file : " + file.getOriginalFilename());
		
		//파일 저장 폴더 지정하기
		String path = request.getSession().getServletContext()
				.getRealPath("resources/uploadFiles");
		System.out.println("path : " + path);
		
		try {
			file.transferTo(new File(path + "\\" + file.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {			
			e.printStackTrace();
		}
		
		return "home";
	}

	@RequestMapping("fdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, 
			@RequestParam("filename") String fileName) {
		
		String path = request.getSession().getServletContext()
				.getRealPath("resources/uploadFiles");
		String filePath = path + "\\" + fileName;
		File downFile = new File(filePath);		
		System.out.println("filePath : " + filePath);
		//ModelAndView(java.lang.String viewName, java.lang.String modelName, java.lang.Object modelObject)
		return new ModelAndView("filedown", "downFile", downFile);
	}

	@RequestMapping(value = "moveAjax.do", method = RequestMethod.GET)
	public String moveAjaxPage() {
		return "test/testAjaxPage";
	}
	

	@RequestMapping(value = "test1.do", method = RequestMethod.POST)
	public void test1Method(Sample command, HttpServletResponse response) throws IOException {
		/*logger.info("test1.do method run...");*/

		System.out.println("command : " + command);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		if (command.getName().equals("신사임당")) {
			out.append("ok");
			out.flush();
		} else {
			out.append("fail");
			out.flush();
		}

		out.close();
	}

	@RequestMapping(value = "test2.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody // 결과를 response 객체에 담아서 보내라는 뜻의 어노테이션임
	public String test2Method(HttpServletResponse response) throws IOException {
		/*logger.info("test2.do method run...");*/

		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", "홍길동");
		job.put("content", "json 객체를 뷰로 리턴하는 테스트 글입니다.");

		return job.toJSONString();
	}

	@RequestMapping(value = "test3.do", method = RequestMethod.POST, produces = "application/json; application/text; application/xml; charset=utf8")
	@ResponseBody
	public void test3Method(HttpServletResponse response) throws IOException {
		/*logger.info("test3.do method run....");*/
		// List를 json 배열로 만들어서 요청한 뷰로 전송 처리함
		response.setContentType("application/json; charset=utf-8");
		List<User> list = new ArrayList<User>();
		list.add(new User("u1234", "p1234", "홍길동", 25, "h1234@iei.org", "010-1234-7777"));
		list.add(new User("u2345", "p2345", "박문수", 33, "p2345@iei.org", "010-2345-7777"));
		list.add(new User("u3456", "p3456", "장영실", 45, "j3456@iei.org", "010-3456-7777"));
		list.add(new User("u4567", "p4567", "이순신", 52, "l4567@iei.org", "010-4567-7777"));
		list.add(new User("u5678", "p5678", "유관순", 16, "y5678@iei.org", "010-5678-7777"));

		// json 배열 객체 생성
		JSONArray jarr = new JSONArray();

		// list 를 jarr 로 복사하기
		for (User user : list) {
			// 추출한 user를 json 객체에 담기
			JSONObject juser = new JSONObject();
			juser.put("userid", user.getUserid());
			juser.put("userpwd", user.getUserpwd());
			juser.put("username", user.getUsername());
			juser.put("age", user.getAge());
			juser.put("email", user.getEmail());
			juser.put("phone", user.getPhone());

			// json 배열에 json 객체 저장
			jarr.add(juser);
		}

		// 전송용 최종 json 객체 선언
		JSONObject sendJson = new JSONObject();
		sendJson.put("list", jarr);

		PrintWriter out = response.getWriter();
		out.print(sendJson.toJSONString());
		out.flush();
		out.close();
	}

	@RequestMapping(value = "test4.do", method = RequestMethod.POST)
	public ModelAndView test4Method(ModelAndView mv, HttpServletResponse response) throws UnsupportedEncodingException {
		/*logger.info("test4.do method run...");*/
		response.setContentType("application/json; charset=utf-8");

		// map 객체를 ModelAndView 에 담아서 JsonView 로 보냄
		Sample samp = new Sample("이순신", 55);
		samp.setName(samp.getName());

		Map<String, Sample> map = new HashMap<String, Sample>();
		map.put("samp", samp);

		mv.addAllObjects(map);
		// 뷰지정 : JsonView 를 bean 으로 등록하고, id를 뷰이름으로 지정함
		mv.setViewName("jsonView");

		return mv; // 뷰리졸버로 전달되고, 요청한 ajax는 json 객체를 받음
	}

	@RequestMapping(value = "test5.do", method = RequestMethod.POST)
	public ResponseEntity<String> test5Method(/* HttpServletRequest request, */@RequestBody String param)
			throws Exception {
		/*logger.info("test5.do method run...");*/
		// request.setCharacterEncoding("utf-8");

		// 전송온 json 문자열을 json 객체로 바꿈
		JSONParser jparser = new JSONParser();
		JSONObject job = (JSONObject) jparser.parse(param);

		String name = (String) job.get("name");
		int age = ((Long) job.get("age")).intValue();

		System.out.println("name : " + name + ", age : " + age);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "test6.do", method = RequestMethod.POST)
	public ResponseEntity<String> test6Method(/* HttpServletRequest request, */@RequestBody String param)
			throws Exception {
		/*logger.info("test5.do method run...");*/
		// request.setCharacterEncoding("utf-8");
		System.out.println("param : " + param);

		// 전송온 jsonArray 문자열을 jsonArray 객체로 바꿈
		JSONParser jparser = new JSONParser();
		JSONArray jarr = (JSONArray) jparser.parse(param);
		System.out.println("jarr size : " + jarr.size());

		for (int i = 0; i < jarr.size(); i++) {
			JSONObject job = (JSONObject) jarr.get(i);
			String name = (String) job.get("name");
			int age = ((Long) job.get("age")).intValue();

			System.out.println("name : " + name + ", age : " + age);
		}

		// 정상 완료됨을 클라이언트로 성공값 보냄
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}

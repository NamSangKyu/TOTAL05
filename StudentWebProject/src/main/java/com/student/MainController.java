package com.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private StudentService service;

	public MainController(StudentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main() {
		return "student_search";
	}
	@RequestMapping("/reset.do")
	public String reset(HttpServletResponse response) {
		List<StudentDTO> list = service.selectAllStudent();
		JSONObject obj = new JSONObject();
		if(list.size() == 0) {
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "None");
		}else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/search.do")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String mode = request.getParameter("mode");
		String search = request.getParameter("search");
		List<StudentDTO> list = service.selectStudentMode(mode,search);
		JSONObject obj = new JSONObject();
		if(list.size() == 0) {
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "None");
		}else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		try {
			System.out.println(obj.toString());
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int error_code = Integer.parseInt(request.getParameter("code_number"));
		String content = request.getParameter("content");
		int count = service.insertLog(log_date,error_code,content);
		try {
			response.getWriter().write(count + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

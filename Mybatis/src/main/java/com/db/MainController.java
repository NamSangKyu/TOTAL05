package com.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private StudentService service;

	public MainController(StudentService service) {
		this.service = service;
	}
	
	@RequestMapping("/main.do")
	public String main(Model model) {
		List<StudentDTO> list = service.selectAllStudent();
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
		model.addAttribute("list",list);
		return "main";
	}
	
	@RequestMapping("/register.do")
	public String insert(HttpServletRequest request,Model model) {
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		int major = Integer.parseInt(request.getParameter("major"));
		double score = Double.parseDouble(request.getParameter("score"));
		
		int count = service.insertStudent(new StudentDTO(sno, name, major, score));
		System.out.println(count + "건 등록완료");
		return main(model);
	}
	
	@RequestMapping("/dataUpdate.do")
	public String dataUpdate(HttpServletRequest request, Model model) {
		//데이터 수정인지? 삭제인지 구분하여 해당하는 일을 수행
		String command = request.getParameter("command");
		String sno = request.getParameter("sno");
		if(command.equals("update")) {
			String name = request.getParameter("name");
			int major = Integer.parseInt(request.getParameter("major"));
			double score = Double.parseDouble(request.getParameter("score"));
			//수정
			int count = service.updateStudent(new StudentDTO(sno,name,major,score));
			System.out.println("수정 : " + count);
		}else {
			//삭제
			int count = service.deleteStudent(sno);
			System.out.println("삭제 : " + count);
		}
				
		
		return main(model);
	}
	
}




package book;

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
	private BookService service;

	public MainController(BookService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main() {
		return "book_insert";
	}
	@RequestMapping("/all.do")
	public String reset(HttpServletResponse response) {
		List<BookDTO> list = service.selectAllBook();
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
	
	@RequestMapping("/insert.do")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		String wdate = request.getParameter("wdate");
		int count = 0;
		JSONObject obj = new JSONObject();
		try {
			BookDTO dto = new BookDTO(bno, title, writer, publisher, wdate);
		count = service.insertBook(dto);
		if(count == 0) {
			obj.put("code", 500);
			obj.put("message", "도서정보 데이터 등록에 실패하였습니다.\n받은 데이터"+dto.toString());
		}else {
			obj.put("code", 200);
			List<BookDTO> list = service.selectAllBook();
			JSONArray arr = new JSONArray(list);
			obj.put("result", arr);
			obj.put("message", "도서정보가 정상적으로 등록 되었습니다.");
		}
		
		}catch (Exception e) {
			obj.put("code", 500);
			obj.put("message", "도서정보 데이터 등록에 실패하였습니다.");
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

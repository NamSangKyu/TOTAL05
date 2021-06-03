package book;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	private BookMapper mapper;

	

	public BookService(BookMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<BookDTO> selectAllBook() {
		return mapper.selectAllBook();
	}

	public int insertLog(String log_date, int error_code, String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("error_code", error_code);
		map.put("content", content);
		return mapper.insertLog(map);
	}

	public int insertBook(BookDTO bookDTO) {
		int count = mapper.insertBook(bookDTO);
		return count; 
	}
	
	
	
}

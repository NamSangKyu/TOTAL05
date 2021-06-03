package book;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

	List<BookDTO> selectAllBook();
	int insertLog(HashMap<String, Object> map);
	int insertBook(BookDTO bookDTO);
	int updateBook(BookDTO bookDTO);
	int deleteBook(String bno);
	List<BookDTO> selectBook(String title);
}

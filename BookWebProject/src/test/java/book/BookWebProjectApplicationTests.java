package book;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class BookWebProjectApplicationTests {

	@Autowired
	BookMapper mapper;
	
	@DisplayName("도서 등록 테스트")
	@Test
	void insertBookTest() {
		BookDTO dto = new BookDTO("891245671234", "자바 프로그래밍", "홍길동", "J테스트", "2020-02-19");
		try {
		int count = mapper.insertBook(dto);
		if(count == 1)
			System.out.println("데이터 추가 성공");
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@DisplayName("도서 검색 테스트")
	@Test
	void searchBookTest() {
		String str = "자바";
		try {
		List<BookDTO> list = mapper.selectBook(str);
		if(list.size()==0)
			fail("데이터 조회 실패");
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@DisplayName("도서 삭제 테스트")
	@Test
	void deleteBookTest() {
		String bno = "891245671234";
		try {
		int count = mapper.deleteBook(bno);
		if(count == 0)
			fail("데이터 삭제 실패");
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}

}

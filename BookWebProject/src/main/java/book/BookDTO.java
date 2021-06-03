package book;

import org.apache.ibatis.type.Alias;

@Alias("book")
public class BookDTO {
	private String bno;
	private String title;
	private String writer;
	private String publisher;
	private String wdate;
	public BookDTO(String bno, String title, String writer, String publisher, String wdate) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.wdate = wdate;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "BookDTO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", publisher=" + publisher
				+ ", wdate=" + wdate + "]";
	}
	
	
	
}

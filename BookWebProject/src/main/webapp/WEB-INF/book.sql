create table book(
	bno varchar2(10 byte) primary key,
	title varchar2(500 byte),
	writer varchar2(50 byte),
	publisher varchar2(50 byte),
	wdate date
)
Insert into SCOTT1.BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('A0001','C언어','홍길동','개인출판',to_date('21/02/01','RR/MM/DD'));
Insert into SCOTT1.BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('A0002','JAVA','홍길동','개인출판',to_date('21/02/02','RR/MM/DD'));

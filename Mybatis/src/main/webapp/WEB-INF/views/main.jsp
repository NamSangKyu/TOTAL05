<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	form{
	text-align: center;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".btn_update").click(function() {
			$(this).parent().find("input[name='command']").val("update");
		});
		$(".btn_delete").click(function() {
			$(this).parent().find("input[name='command']").val("delete");
		});
	});
	
</script>
</head>
<body>
	<form id="input_frm" action="register.do" method="post">
		<input type="text" name="sno" placeholder="학번">
		<input type="text" name="name" placeholder="이름">
		<input type="text" name="major" placeholder="학과">
		<input type="text" name="score" placeholder="점수">
		<button>등록</button>
	</form>
	<hr>
	<c:forEach var="dto" items="${requestScope.list }" varStatus="i"> 
	<p>
		<form class="data_frm" action="dataUpdate.do">
			<input type="text" name="sno" placeholder="학번" value="${dto.sno }" readonly>
			<input type="text" name="name" placeholder="이름" value="${dto.name }">
			<input type="text" name="major" placeholder="학과" value="${dto.major }">
			<input type="text" name="score" placeholder="점수" value="${dto.score }">
			<input type="hidden" name="command" id="command" value="${i.count }">
			<button class="btn_update">수정</button>
			<button class="btn_delete">삭제</button>
		</form>
	</p>	
	</c:forEach>
</body>
</html>













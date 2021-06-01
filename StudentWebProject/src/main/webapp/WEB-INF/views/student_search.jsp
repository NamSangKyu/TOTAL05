<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생정보 조회 페이지</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
		text-align: center;
	}
	span{
		width:100px;
		display: inline-block;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#btn_search").click(function(){
			var str = $("#frm").serialize();
			$.ajax({
				url:"search.do",
				data:str,
				dataType:"json",
				success:function(r){
					var tag = "";
					if(r.code == 200){
						var arr = r.result;
						for(i=0;i<arr.length;i++){
							tag += "<p><span>" + arr[i].sno + "</span><span>" + arr[i].name+ "</span><span>" + arr[i].major+ "</span><span>" + arr[i].score + "</span></p>"; 
						}
					}else{
						tag = r.message;
					}
					$("#result").html(tag);
				}
			});
		});
		$("#btn_reset").click(function(){
			$.ajax({
				url:"reset.do",
				dataType:"json",
				success:function(r){
					var tag = "";
					if(r.code == 200){
						var arr = r.result;
						for(i=0;i<arr.length;i++){
							tag += "<p><span>" + arr[i].sno + "</span><span>" + arr[i].name+ "</span><span>" + arr[i].major+ "</span><span>" + arr[i].score + "</span></p>"; 
						}
					}else{
						tag = r.message;
					}
					$("#result").html(tag);
				}
			});
		});
		$("#btn_reset").click();
	});
</script>
</head>
<body>
<form id="frm">
	<select name="mode">
		<option value="sno">학번</option>
		<option value="name">이름</option>
		<option value="major">학과</option>
	</select>
	<input type="text" name="search"><button type="button" id="btn_search">검색</button>
	<button type="button" id="btn_reset">리셋</button>
</form>
<hr>
<div id="result"></div>
</body>
</html>
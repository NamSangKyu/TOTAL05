<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서정보 등록 페이지</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
		text-align: center;
	}
	span{
		width:200px;
		padding:10px;
		display: inline-block;
	}
	#frm{
		padding:10px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#btn_insert").click(function(){
			var str = $("#frm").serialize();
			$.ajax({
				url:"insert.do",
				data:str,
				dataType:"json",
				success:function(r){
					var tag = "";
					if(r.code == 200){
						var arr = r.result;
						for(i=0;i<arr.length;i++){
							tag += "<p><span>" + arr[i].bno + "</span><span>" + arr[i].title+ "</span><span>" + arr[i].writer+ "</span><span>" + arr[i].publisher + "</span><span>" + arr[i].wdate+ "</span></p>"; 
						}
					}else{
						tag = r.message;
					}
					$("#result").html(tag);
				}
			});
		});
		
		$.ajax({
			url:"all.do",
			dataType:"json",
			success:function(r){
				var tag = "";
				if(r.code == 200){
					var arr = r.result;
					for(i=0;i<arr.length;i++){
						tag += "<p><span>" + arr[i].bno + "</span><span>" + arr[i].title+ "</span><span>" + arr[i].writer+ "</span><span>" + arr[i].publisher + "</span><span>" + arr[i].wdate+ "</span></p>"; 
					}
				}else{
					tag = r.message;
				}
				$("#result").html(tag);
			}
		});
		
	});
</script>
</head>
<body>
<form id="frm">
	<input type="text" name="bno" >
	<input type="text" name="title">
	<input type="text" name="writer">
	<input type="text" name="publisher">
	<input type="date" name="wdate">
	<button type="button" id="btn_insert">도서 등록</button>
</form>
<hr>
<div id="result"></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="plugins/city-picker.css">
<script type="text/javascript" src="plugins/jquery.min.js"></script>
<script src="plugins/city-picker.data.js"></script>
<script src="plugins/city-picker.js"></script>
</head>
	<script type="text/javascript">
		$(function() {
			$("#in").citypicker();
			$("#btn").click(function() {
				$("#in").citypicker("reset");
			})
		}) 
	</script>
<body>
	<div style="position: relative;">
		<input readonly type="text" data-toggle="city-picker">
	</div>
	
	<div style="position: relative;">
		<input id="in" type="text">
		<input type="button" value="重置" id="btn"> 
	</div>
</body>
</html>
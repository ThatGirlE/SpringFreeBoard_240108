<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	글번호 : ${fboardDto.fbnum }<br><br>
	글제목 : ${fboardDto.fbtitle }<br><br>
	글쓴이 : ${fboardDto.fbname }<br><br>
	글내용 : ${fboardDto.fbcontent }<br><br>
	조회수 : ${fboardDto.fbhit }<br><br>
	등록일 : ${fboardDto.fbdate }<br><br>
</body>
</html>
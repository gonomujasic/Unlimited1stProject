<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Unlimited.min.Notice.model.NoticeDTO"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
</head>
<% 

Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;
try{
Context context = new InitialContext();
DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
connection = dataSource.getConnection();
List<NoticeDTO> list = new ArrayList<NoticeDTO>();

String sql = "select * from (select rownum rnum, no, writer, title, "
		+ " write_date " + 
		" from (select * from noticelist order by no desc))"
		+ " where rnum >=? and rnum <=?";     //이거 역순으로 안되냐
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, 1);
		preparedStatement.setInt(2, 5);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNo(resultSet.getInt("no"));
			noticeDTO.setWriter(resultSet.getString("writer"));
			noticeDTO.setTitle(resultSet.getString("title"));
			noticeDTO.setWrite_date(resultSet.getString("write_date"));
			System.out.println(noticeDTO);
			list.add(noticeDTO);
		}
		request.setAttribute("noticelist", list);
		
List<NoticeDTO> list2 = new ArrayList<NoticeDTO>();
sql = "select * from (select rownum rnum, study_post_index, id, study_post_title, "
		+ " study_post_date " + 
		" from (select * from studyboard order by study_post_index desc))"
		+ " where rnum >=? and rnum <=?";     //이거 역순으로 안되냐
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, 1);
		preparedStatement.setInt(2, 5);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNo(resultSet.getInt("study_post_index"));
			noticeDTO.setWriter(resultSet.getString("study_post_writer"));
			noticeDTO.setTitle(resultSet.getString("study_post_title"));
			noticeDTO.setWrite_date(resultSet.getString("study_post_date"));
			System.out.println(noticeDTO);
			list.add(noticeDTO);
		}
		request.setAttribute("studylist", list2);
		
List<NoticeDTO> list3 = new ArrayList<NoticeDTO>();
sql = "select * from (select rownum rnum, qna_number, id, qna_title, "
		+ " qna_date " + 
		" from (select * from qna order by qna_number desc))"
		+ " where rnum >=? and rnum <=?";     //이거 역순으로 안되냐
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, 1);
		preparedStatement.setInt(2, 5);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNo(resultSet.getInt("qna_number"));
			noticeDTO.setWriter(resultSet.getString("id"));
			noticeDTO.setTitle(resultSet.getString("qna_title"));
			noticeDTO.setWrite_date(resultSet.getString("qna_date"));
			System.out.println(noticeDTO);
			list.add(noticeDTO);
		}
		request.setAttribute("qnalist", list3);
		

}catch(Exception e){
	e.printStackTrace();
}finally {
	try {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
}

%>
<body style="background-color:transparent">
<div class="container">
<table class="table" style="text-align: center; width:50">
<caption>공지사항</caption>
<tr> 
<td>
번호 
</td>
<td>
제목 
</td>
<td>
작성일 
</td>
</tr>
<c:forEach items="${noticelist}" var="noticedto">
<tr>
<td>
${noticedto.no}
</td>
<td>
<a href="./NoticeDetailService.notice?no=${noticedto.no}">${noticedto.title}</a>
</td>
<td>
${noticedto.write_date}
</td>
</tr>
</c:forEach>
</table>
</div>

<div class="container">
<table class="table" style="text-align: center; width:50">
<caption>공부 게시판</caption>
<tr> 
<td>
번호 
</td>
<td>
제목 
</td>
<td>
작성일 
</td>
<td>
작성자 
</td>
</tr>
<c:forEach items="${studylist}" var="studydto">
<tr>
<td>
${studydto.no}
</td>
<td>
<a href="./NoticeDetailService.notice?no=${studydto.no}">${studydto.title}</a>
<!-- 이거유알엘 -->
</td>
<td>
${studydto.write_date}
</td>
<td>
${studydto.writer}
</td>
</tr>
</c:forEach>
</table>
</div>


<div class="container" id="">
<table class="table" style="text-align: center; width:50">
<caption>QNA</caption>
<tr> 
<td>
번호 
</td>
<td>
제목 
</td>
<td>
작성일 
</td>
<td>
작성자 
</td>
</tr>
<c:forEach items="${qnalist}" var="studydto">
<tr>
<td>
${studydto.no}
</td>
<td>
<a href="./NoticeDetailService.notice?no=${studydto.no}">${studydto.title}</a>
<!-- 이거유알엘 -->
</td>
<td>
${studydto.write_date}
</td>
<td>
${studydto.writer}
</td>

</tr>
</c:forEach>
</table>
</div>
</body>
</html>
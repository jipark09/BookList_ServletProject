<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="kr.ac.green.Book" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>list.jsp</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<div class="container">
	<h1>Book List</h1>
	<a href="add.book">책 등록하기</a>
	<hr>
	<div class="d-flex justify-content-center">
		<form action="findByTitle.book" method="get">
			책 제목 <input type="text" name="findTitle" />
			<input type="submit" value="검색"class="btn btn-primary"/>
		</form>
	</div>
	<hr>
	<table class="table table-striped">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">저자</th>	
			<th scope="col">가격</th>		
		</tr>
		<c:choose>
			<c:when test="${ empty list }">
			<tr>
				<td colspan="4">no data</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${ list }" var="book">
				<tr onclick="showBookInfo(${book.num})">
					<th scope="row">${book.num }</th>
					<%-- <td>${book.num}</td> --%>
					<td>${book.title}</td>
					<td>${book.writer}</td>
					<td>${book.price}</td>
				</tr>		
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<th colspan ="4" scope="row">
				<c:forEach var="i" begin="1" end="${ pageCount }">
					<c:choose>
						<c:when test="${ i == pageNum }">
						<div class="d-flex justify-content-center">
							[${ i }]
						</div>
						</c:when>
						<c:otherwise>
							<a href="list.book?pageNum=${ i }">[${ i }]</a>						
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</th>
		</tr>
	</table>
	<script>
		function showBookInfo() {
			location.href="view.book?num=" + arguments[0];
		}
	</script>
</div>
</body>
</html>











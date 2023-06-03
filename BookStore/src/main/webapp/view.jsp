<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>view.jsp</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>

</head>
<body>
<div class="container">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<c:if test="${not empty wrongPassword }">
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	</c:if>
	<h1>Book View</h1>
	<a href="list.book">�������</a>
	<hr>
	<jsp:useBean scope="request" id="book" class="kr.ac.green.Book" />
	<form class="row gy-2 gx-3 align-items-center">
		<div class="col-auto">
			<label for="inputNum" class="form-label">��ȣ</label>
			<input type="text" name="num" value="${book.num }" id="inputNum" class="form-control" aria-label="readonly input example" readonly/>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputT" class="form-label">����</label>
				<input type="text" name="title" value="${book.title }" id="inputT" class="form-control" readonly/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputT" class="form-label">����</label>
				<input type="text" name="writer" value="${book.writer }" id="inputT" class="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPrice" class="form-label">����</label>
				<input type="text" name="price" value="${book.price }" id="inputPrice" class="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPub" class="form-label">���ǻ�</label>
				<input type="text" name="publisher" value="${book.publisher }" id="inputPub" class="form-control"/>
			</div>
		</div>
		<div class="mb-3">
			<label for="inputComment" class="form-label">�ڸ�Ʈ</label>
			<textarea rows="3" cols="20" name="comment" id="inputComment" class="form-control">${book.comment }</textarea>
		</div>
	
			���� ���� �� ��й�ȣ �Է� <input type="password" name="pw" class="form-control">
		<div class="col-auto">
		<input type="button" value="�����ϱ�" onclick="updateBook(this.form, 'modify')" class="btn btn-primary">
		<input type="button" value="�����ϱ�" onclick="updateBook(this.form, 'delete')" class="btn btn-primary">
		<input type="reset" value="����" class="btn btn-primary">
		</div>
	</form>
	<script>
		function updateBook(updateForm, what) {			
			updateForm.action = what + ".book";
			updateForm.method = "post";
			updateForm.submit();
		}
	</script>
</div>
</body>
</html>






<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>add.jsp</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
</head>
<body>
<div class="container">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<h1>Book add</h1>
	<a href="list.book">�������</a>
	<hr>
	<form action="doAdd.book" method="post" class="row gy-2 gx-3 align-items-center">
		<div class="col-auto">
			<label for="inputTitle" class="form-label">����</label>
			<input type="text" name="title" class="form-control" id="inputTitle"/>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputW" class="form-label">����</label>
				<input type="text" name="writer" class="form-control" id="inputW"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPassword" class="form-label">��й�ȣ</label>
				<input type="password" name="pw"  class="form-control" id="inputPassword"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPrice" class="form-label">����</label>
				<input type="text" name="price" class="form-control" id="inputPrice"/>
			</div>
		</div>
		<div class="col-auto">
			<label for="inputPub" class="form-label">���ǻ�</label>
			<input type="text" name="publisher" class="form-control" id="inputPub"/>
		</div>
		<br>
		<div class="mb-3">
			<label for="inputComment" class="form-label">�ڸ�Ʈ</label>
			<textarea rows="3" cols="20" name="comment" class="form-control" id="inputComment"></textarea>
			<br>
			<button type="submit" class="btn btn-primary">�߰��ϱ�</button>
			<button type="reset" class="btn btn-primary" >����</button>
		</div>
		
	</form>
</div>
</body>
</html>
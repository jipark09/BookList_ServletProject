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
	<a href="list.book">목록으로</a>
	<hr>
	<form action="doAdd.book" method="post" class="row gy-2 gx-3 align-items-center">
		<div class="col-auto">
			<label for="inputTitle" class="form-label">제목</label>
			<input type="text" name="title" class="form-control" id="inputTitle"/>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputW" class="form-label">저자</label>
				<input type="text" name="writer" class="form-control" id="inputW"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPassword" class="form-label">비밀번호</label>
				<input type="password" name="pw"  class="form-control" id="inputPassword"/>
			</div>
		</div>
		<div class="row">
			<div class="col-auto">
				<label for="inputPrice" class="form-label">가격</label>
				<input type="text" name="price" class="form-control" id="inputPrice"/>
			</div>
		</div>
		<div class="col-auto">
			<label for="inputPub" class="form-label">출판사</label>
			<input type="text" name="publisher" class="form-control" id="inputPub"/>
		</div>
		<br>
		<div class="mb-3">
			<label for="inputComment" class="form-label">코멘트</label>
			<textarea rows="3" cols="20" name="comment" class="form-control" id="inputComment"></textarea>
			<br>
			<button type="submit" class="btn btn-primary">추가하기</button>
			<button type="reset" class="btn btn-primary" >리셋</button>
		</div>
		
	</form>
</div>
</body>
</html>
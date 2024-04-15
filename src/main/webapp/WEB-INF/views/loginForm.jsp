<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<title>DOKKY</title>
<style>
* {
	box-sizing: border-box;
	margin: 0px;
	padding: 0px;
}

a {
	text-decoration: none;
	color: black;
}

.noto-sans400 {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
}

.noto-sans700 {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 700;
	font-style: normal;
}

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 100px;
}

#logo {
	width: 200px;
	height: 60px;
	text-align: center;
	font-size: 3rem;
	line-height: 60px;
	margin-bottom: 30px;
}

#login-form {
	width: 500px;
	height: 500px;
	border-top: 1px rgb(162, 164, 168, 0.4) solid;
}

#id, #pwd, #login-btn {
	display: block;
	width: 100%;
	height: 50px;
	margin-bottom: 30px;
}

#login-btn {
	font-size: 1.2rem;
	margin-top: 50px;
	color: white;
	background-color: rgb(108, 196, 255);
	border-color: white;
	border-style: hidden;
}

#login-btn:hover {
	background-color: rgb(91, 163, 211);
}

#id, #pwd {
	padding: 10px;
	font-size: 1rem;
	margin-top: 10px;
}

#msg, #register {
	text-align: center;
	margin-top: 30px;
	margin-bottom: 30px;
}

#msg {
	font-size: 1.2rem;
	color: red;
}

#register {
	font-size: 0.8rem;
}

#rememberId {
	width: 20px;
	height: 20px;
	margin-left: 10px;
}

.checkbox-container {
	display: flex;
	align-items: center;
}
</style>
</head>
<body>
	<div class="container">
		<div id="logo" class="noto-sans700">
			<a href="<c:url value='/' />">DOKKY</a>
		</div>
		<form id="login-form" class="noto-sans400" action="<c:url value='/login/login' />"
			method="post" onsubmit="return formCheck(this)">
			<div id="msg">${URLDecoder.decode(param.msg)}</div>
			<label for="id">아이디</label> <input type="text" id="id" name="id" class="noto-sans400"
				value="${cookie.id.value}" placeholder="아이디를 입력해주세요" autofocus /> <label
				for="pwd">비밀번호</label> <input type="password" id="pwd" name="pwd"
				placeholder="비밀번호를 입력해주세요" />
			<div class="checkbox-container">
				<label for="rememberId">아이디 기억</label><input type="checkbox"
					id="rememberId" name="rememberId"
					${empty cookie.id.value ? "" : "checked"} />
			</div>
			<button type="submit" id="login-btn" class="noto-sans400">로그인</button>
			<div id="register">
				<a href="<c:url value='/register/add' />">회원가입</a>
			</div>
			<input type="hidden" name="toURL" value="${param.toURL}" /> <input
				type="hidden" name="page" value="${param.page}" /> <input
				type="hidden" name="pageSize" value="${param.pageSize}" /> <input
				type="hidden" name="option" value="${param.option}" /> <input
				type="hidden" name="keyword" value="${param.keyword}" />
		</form>
	</div>
	<script>
		function formCheck(form) {
			if (form.id.value.length == 0) {
				document.querySelector("#msg").innerHTML = '아이디를 입력해주세요!'
				form.id.select();
				return false;
			}
			if (form.pwd.value.length == 0) {
				document.querySelector("#msg").innerHTML = '비밀번호를 입력해주세요!'
				form.pwd.select();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
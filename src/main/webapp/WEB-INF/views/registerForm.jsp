<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<c:url value="/register/add" var="addUrl" />
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

#login-form input, #login-form button {
	display: block;
	width: 100%;
	height: 50px;
	margin-bottom: 30px;
}

#login-form button {
	font-size: 1.2rem;
	margin-top: 50px;
	color: white;
	background-color: rgb(108, 196, 255);
	border-color: white;
	border-style: hidden;
}

#login-form button:hover {
	background-color: rgb(91, 163, 211);
}

#login-form input {
	padding: 10px;
	font-size: 1rem;
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

.login-text {
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<div id="logo" class="noto-sans700">
			<a href="<c:url value='/' />">DOKKY</a>
		</div>
		<form:form id="login-form" class="noto-sans400" modelAttribute="userDto" action="${addUrl}"
			method="post" onsubmit="return formCheck(this)">
			<div id="msg">${param.msg}<form:errors path="birth" />
			</div>
			<label for="id" class="login-text">아이디</label>
			<input type="text" id="id" name="id" class="noto-sans400" placeholder="4~15자로 입력해주세요 (영문,숫자)" maxlength="15" />
			<label for="pwd" class="login-text">비밀번호</label>
			<input type="password" id="pwd" name="pwd" class="noto-sans400" 
				placeholder="6~15자로 입력해주세요 (영문+숫자)" maxlength="15" />
			<label for="pwd-check" class="login-text">비밀번호 확인</label>
			<input type="password" id="pwd-check" class="noto-sans400" 
				placeholder="6~15자로 입력해주세요 (영문+숫자)" maxlength="15" />
			<label for="email" class="login-text">이메일</label>
			<input type="text" id="email" name="email" class="noto-sans400"
				placeholder="example@example.com" maxlength="50" />
			<label for="name" class="login-text">이름</label>
			<input type="text" id="name" name="name" class="noto-sans400" placeholder="이름을 입력해 주세요" maxlength="30" />
			<label for="nickname" class="login-text">닉네임</label>
			<input type="text" id="nickname" name="nickname" class="noto-sans400"
				placeholder="닉네임을 입력해 주세요" maxlength="15" />
			<label for="birth" class="login-text">생년월일</label>
			<input type="text" id="birth" name="birth" class="noto-sans400" placeholder="ex)19940707" maxlength="8" />
			<button type="submit" id="register-btn" class="noto-sans400">회원가입</button>
		</form:form>
	</div>
	<script>
		function formCheck(form) {
			if (!(4 <= form.id.value.length && form.id.value.length <= 15)) {
				document.querySelector("#msg").innerHTML = '아이디는 4~15자로 입력해주세요!';
				form.id.select();
				return false;
			}
			
			if (form.id.value.includes(" ")) {
				document.querySelector("#msg").innerHTML = '아이디를 공백없이 입력해주세요!';
				form.id.select();
				return false;
			}
			
			if (!idCheck(form.id.value)) {
				document.querySelector("#msg").innerHTML = '아이디를 영문이나 숫자로만 입력해주세요!';
				form.id.select();
				return false;
			}
			
			if (!(6 <= form.pwd.value.length && form.pwd.value.length <= 15)) {
				document.querySelector("#msg").innerHTML = '비밀번호는 6~15자로 입력해주세요!';
				form.pwd.select();
				return false;
			}
			
			if (form.pwd.value.includes(" ")) {
				document.querySelector("#msg").innerHTML = '비밀번호를 공백없이 입력해주세요!';
				form.pwd.select();
				return false;
			}
			
			if (!pwdCheck(form.pwd.value)) {
				document.querySelector("#msg").innerHTML = '비밀번호에 영문과 숫자를 반드시 포함해 주세요!';
				form.pwd.select();
				return false;
			}
			
			if (form.pwd.value != document.querySelector("#pwd-check").value) {
				document.querySelector("#msg").innerHTML = '비밀번호 확인이 비밀번호와 일치하지 않습니다!';
				document.querySelector("#pwd-check").select();
				return false;
			}
			
			if (form.email.value.length == 0) {
				document.querySelector("#msg").innerHTML = '이메일을 입력해주세요!';
				form.email.select();
				return false;
			}
			
			if (form.email.value.includes(" ")) {
				document.querySelector("#msg").innerHTML = '이메일을 공백없이 입력해주세요!';
				form.email.select();
				return false;
			}
			
			if (!emailCheck(form.email.value)) {
				document.querySelector("#msg").innerHTML = '이메일을 형식에 맞게 정확히 입력해주세요!';
				form.email.select();
				return false;
			}
			
			if (form.name.value.length == 0) {
				document.querySelector("#msg").innerHTML = '이름을 입력해주세요!';
				form.name.select();
				return false;
			}
			if (form.nickname.value.length == 0) {
				document.querySelector("#msg").innerHTML = '닉네임을 입력해주세요!';
				form.nickname.select();
				return false;
			}
			if (form.birth.value.length == 0) {
				document.querySelector("#msg").innerHTML = '생년월일을 입력해주세요!';
				form.birth.select();
				return false;
			}
			if (!birthCheck(form.birth.value)) {
				document.querySelector("#msg").innerHTML = '생년월일을 8자리로 형식에 맞게 작성해주세요! ex)19940707';
				form.birth.select();
				return false;
			}
			return true;
		}
		
		function idCheck(id) {
			let regex = /^[a-zA-Z0-9]+$/;
			return regex.test(id);
		}
		
		function pwdCheck(pwd) {
			let regex = /^(?=.*[a-zA-z])(?=.*[0-9])[a-zA-Z0-9]+$/;
			return regex.test(pwd);
		}
		
		function emailCheck(email) {
			let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			return regex.test(email);
		}
		
		function birthCheck(birth) {
			let regex = /^\d{8}$/;
			
			if (!regex.test(birth)) return false;
			
			let year = parseInt(birth.substring(0, 4));
			let month = parseInt(birth.substring(4, 6));
			let day = parseInt(birth.substring(6, 8));
			
			if (year < 1900 || year > new Date().getFullYear() || month < 1 || month > 12 || day < 1 || day > 31) return false;
			
			return true;
		}
	</script>
</body>
</html>
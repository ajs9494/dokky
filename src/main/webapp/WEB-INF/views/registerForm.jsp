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
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
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

#login-form input, #register-btn {
	display: block;
	width: 100%;
	height: 50px;
	margin-bottom: 30px;
}

#id, #nickname {
	margin-bottom: 10px !important;
}

#id-check-btn, #nick-check-btn {
	margin-bottom: 20px;
	border: 0;
	cursor: pointer;
	background-color: white;
	font-size: 0.9rem;
	color: rgba(0,0,0,0.8);
}

#id-check-msg, #nick-check-msg {
	font-size: 0.9rem;
	color: rgba(0,0,0,0.8);
}

#id-check-btn:hover, #nick-check-btn:hover {
	color: rgba(0,0,0,1);
}

#register-btn {
	font-size: 1.2rem;
	margin-top: 50px;
	color: white;
	background-color: rgb(108, 196, 255);
	border-color: white;
	border-style: hidden;
}

#register-btn:hover {
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
			<div id="id-check-cnt"><button type="button" id="id-check-btn" class="noto-sans700">아이디 중복체크</button><span id="id-check-msg"></span></div>
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
				<div id="nick-check-cnt"><button type="button" id="nick-check-btn" class="noto-sans700">닉네임 중복체크</button><span id="nick-check-msg"></span></div>
			<label for="birth" class="login-text">생년월일</label>
			<input type="text" id="birth" name="birth" class="noto-sans400" placeholder="ex)19940707" maxlength="8" />
			<button type="submit" id="register-btn" class="noto-sans400">회원가입</button>
		</form:form>
		<input type="checkbox" style="display:none" id="id-checkbox">
		<input type="checkbox" style="display:none" id="nick-checkbox">
	</div>
	<script>
		$(document).ready(function() {
			$("#id-check-btn").click(function() {
				let id = document.querySelector("#id");
				
				// 유효성 검증 조건에 만족하지 않은 상태로 중복체크버튼 누를시 중복검사를 하지 않고 메시지 출력
				if (!(4 <= id.value.length && id.value.length <= 15)) {
					document.querySelector("#msg").innerHTML = '아이디는 4~15자로 입력해주세요!';
					id.select();
					return false;
				}
				
				if (id.value.includes(" ")) {
					document.querySelector("#msg").innerHTML = '아이디를 공백없이 입력해주세요!';
					id.select();
					return false;
				}
				
				if (!idCheck(id.value)) {
					document.querySelector("#msg").innerHTML = '아이디를 영문이나 숫자로만 입력해주세요!';
					id.select();
					return false;
				}
				
				// 유효성 검증 통과 시 ajax 호출
				$.ajax({
					type: 'GET',
					url: '<c:url value="/register/check"/>?id='+id.value,
					headers: { "content-type": "application/json" },
					success: function(result) {
						// 중복체크 결과가 boolean으로 반환됨
						if(result) {
							// 결과확인용 텍스트 출력 및 중복이면 숨겨진 체크박스 체크해제, 중복이 아니면 체크박스 체크
							// 중복이 아닌경우에만 회원가입 폼이 제출된다
							$("#id-check-msg").text(" - 체크완료");
							$("#id-check-msg").css("color", "rgba(0,155,255)");
							$("#id-checkbox").prop("checked", true);
						} else {
							$("#id-check-msg").text(" - 아이디가 중복입니다!");
							$("#id-check-msg").css("color", "red");
							$("#id-checkbox").prop("checked", false);
							id.select();
						}
					},
					error: function() {
						alert("아이디 중복체크에 실패했습니다! 다시 시도해 주세요!");
					}
				})
			})
			
			$("#nick-check-btn").click(function() {
				let nickname = document.querySelector("#nickname");
				
				if (nickname.value.length == 0) {
					document.querySelector("#msg").innerHTML = '닉네임을 입력해주세요!';
					nickname.select();
					return false;
				}
				
				if (nickname.value.includes(" ")) {
					document.querySelector("#msg").innerHTML = '닉네임을 공백없이 입력해주세요!';
					nickname.select();
					return false;
				}
				
				// 유효성 검증 통과 하면 ajax호출
				$.ajax({
					type: 'GET',
					url: '<c:url value="/register/check"/>?nickname='+nickname.value,
					headers: { "content-type": "application/json" },
					success: function(result) {
						// 중복체크 결과가 boolean으로 반환됨
						if(result) {
							// 결과확인용 텍스트 출력 및 중복이면 숨겨진 체크박스 체크해제, 중복이 아니면 체크박스 체크
							// 중복이 아닌경우에만 회원가입 폼이 제출된다
							$("#nick-check-msg").text(" - 체크완료");
							$("#nick-check-msg").css("color", "rgba(0,155,255)");
							$("#nick-checkbox").prop("checked", true);
						} else {
							$("#nick-check-msg").text(" - 닉네임이 중복입니다!");
							$("#nick-check-msg").css("color", "red");
							$("#nick-checkbox").prop("checked", false);
							nickname.select();
						}
					},
					error: function() {
						alert("닉네임 중복체크에 실패했습니다! 다시 시도해 주세요!");
					}
				});
			});
			
			// 아이디 입력칸에 변화 발생시 아이디 중복체크 메시지를 비운다
			$("#id").on("input", function() {
				$("#id-check-msg").text("");
				
			})
			
			// 닉네임 입력칸에 변화 발생시 닉네임 중복체크 메시지를 비운다
			$("#nickname").on("input", function() {
				$("#nick-check-msg").text("");
				
			});
		});
	
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
			
			if (form.nickname.value.includes(" ")) {
				document.querySelector("#msg").innerHTML = '닉네임을 공백없이 입력해주세요!';
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
			
			// 아이디를 재입력 한 뒤 중복체크를 하지 않은경우 메시지 출력
			if ($("#id-check-msg").text() == "") {
				document.querySelector("#msg").innerHTML = '아이디 중복체크를 해주세요!';
				form.id.select();
				return false;
			}
			
			// 아이디가 중복인데 회원가입버튼을 누른경우 메시지 출력
			if ($("#id-checkbox").prop("checked") == false) {
				document.querySelector("#msg").innerHTML = '아이디가 중복입니다!';
				form.id.select();
				return false;
			}
			
			// 닉네임을 재입력 한 뒤 중복체크를 하지 않은경우 메시지 출력
			if ($("#nick-check-msg").text() == "") {
				document.querySelector("#msg").innerHTML = '닉네임 중복체크를 해주세요!';
				form.nickname.select();
				return false;
			}
			
			// 닉네임이 중복인데 회원가입버튼을 누른경우 메시지 출력
			if ($("#nick-checkbox").prop("checked") == false) {
				document.querySelector("#msg").innerHTML = '닉네임이 중복입니다!';
				form.nickname.select();
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
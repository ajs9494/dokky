<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>
<c:set var="hasSessionId"
	value="${pageContext.request.getSession(false).getAttribute('id') ne null}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/3460525fcc.js"
	crossorigin="anonymous"></script>
<title>DOKKY</title>
<style>
	#mypage-container {
		width: 800px;
		margin: auto;
	}
	
	#mypage-header {
		width: 100%;
		font-size: 1.5rem;
		border-bottom: 2px solid black;
		padding-bottom: 10px;
	}
	
	#mypage-cnt {
		display: grid;
		grid-template-columns: 2fr 8fr;
		margin-top: 30px;
		column-gap: 50px;
	}
	
	#mypage-menu {
		display: grid;
		list-style: none;
		grid-template-rows: repeat(5, 50px);
 	    border-top: 1px solid rgb(0, 0, 0, 0.1);
	}
	
	#mypage-menu li {
 		width: 100%;
		line-height: 40px;
		border-bottom: 1px solid rgba(0, 0, 0, 0.1);
	}
	
	#mypage-menu li a {
		display: inline-block;
		width: 100%;
		padding: 5px;
	}
	
	#mypage-menu li a:hover {
		background-color: rgba(0,0,0,0.1);
	}
	
	#profile-cnt {
		display: grid;
		grid-template-columns: 3fr 7fr;
		grid-template-rows: repeat(5, 70px);
		border: 1px solid rgb(0, 0, 0, 0.1);
		border-bottom: 0;
	}
	
	#profile-cnt div {
		line-height: 70px;
		text-align: center;
		border-bottom: 1px solid rgb(0, 0, 0, 0.1);
	}
	
	#profile-cnt div:nth-child(odd) {
		border-right: 1px solid rgb(0, 0, 0, 0.1);
		background-color: 
	}
	
	#change-form {
		width: 100%;
	}
	
	#change-form input, #save-btn, #change-btn {
		display: block;
		width: 100%;
		height: 50px;
		margin-bottom: 30px;
	}

	#nickname {
		margin-bottom: 10px !important;
	}

	#nick-check-btn {
		margin-bottom: 20px;
		border: 0;
		cursor: pointer;
		background-color: white;
		font-size: 0.9rem;
		color: rgba(0,0,0,0.8);
	}
	
	#nick-check-msg {
		font-size: 0.9rem;
		color: rgba(0,0,0,0.8);
	}
	
	#nick-check-btn:hover {
		color: rgba(0,0,0,1);
	}
	
	#save-btn, #change-btn {
		font-size: 1.2rem;
		margin-top: 50px;
		color: white;
		background-color: rgb(108, 196, 255);
		border-color: white;
		border-style: hidden;
		cursor: pointer;
	}

	#save-btn:hover, #change-btn:hover {
		background-color: rgb(91, 163, 211);
	}
	
	#change-form input {
		padding: 10px;
		font-size: 1rem;
	}
	
	#msg {
		text-align: center;
		font-size: 1.2rem;
		color: red;
	}
	
	.login-text {
		margin-bottom: 5px;
	}
	
	#withdrawal-form {
		width: 100%;
		text-align: center;
	}
	
	#withdrawal-text {
		font-size: 1.3rem;
		margin-bottom: 30px;
	}
	
	#withdrawal-btn {
		font-size: 1.1rem;
   		padding: 10px;
   		color: white;
		background-color: rgb(108, 196, 255);
		border-color: white;
		border-style: hidden;
		cursor: pointer;
	}
	
	#withdrawal-btn:hover {
		background-color: rgb(91, 163, 211);
	}
</style>
</head>
<body>
<script>
	let msg = "${msg}";
	if (msg === "INFO_OK") alert("회원정보가 변경되었습니다.");
	if (msg === "PWD_OK") alert("비밀번호가 변경되었습니다.");
	if (msg === "INFO_ERR") alert("회원정보 변경에 실패했습니다! 다시 시도해 주세요!");
	if (msg === "PWD_ERR") alert("비밀번호 변경에 실패했습니다! 다시 시도해 주세요!");
	if (msg === "RMV_ERR") alert("회원탈퇴에 실패했습니다! 다시 시도해 주세요!");
</script>
	<%@include file="header.jsp"%>
	<div id="mypage-container">
		<div id="mypage-header" class="noto-sans700">마이페이지</div>
		<div id="mypage-cnt" class="noto-sans400">
			<ul id="mypage-menu">
				<li id="mypage-profile"><a href="${pageContext.request.contextPath}/mypage/home?menu=profile">프로필</a></li>
				<li id="change-info"><a href="${pageContext.request.contextPath}/mypage/home?menu=info">회원정보 변경</a></li>
				<li id="change-password"><a href="${pageContext.request.contextPath}/mypage/home?menu=password">비밀번호 변경</a></li>
				<li id="withdrawal-membership"><a href="${pageContext.request.contextPath}/mypage/home?menu=withdrawal">회원탈퇴</a></li>
			</ul>
			<div id="mypage-body">
				<c:choose>
					<c:when test="${param.menu eq 'info'}">
						<form id="change-form" class="noto-sans400" action="${pageContext.request.contextPath}/mypage/update" method="post" onsubmit="return formCheckInfo(this)">
							<div id="msg">${param.msg}</div>
							<label for="email" class="login-text">이메일</label>
							<input type="text" id="email" name="email" class="noto-sans400"
								placeholder="example@example.com" maxlength="50" value="${user.email}" />
							<label for="name" class="login-text">이름</label>
							<input type="text" id="name" name="name" class="noto-sans400" placeholder="이름을 입력해 주세요" maxlength="30" value="${user.name}" />
							<label for="nickname" class="login-text">닉네임</label>
							<input type="text" id="nickname" name="nickname" class="noto-sans400"
								placeholder="닉네임을 입력해 주세요" maxlength="15" value="${user.nickname}" />
								<div id="nick-check-cnt"><button type="button" id="nick-check-btn" class="noto-sans700">닉네임 중복체크</button><span id="nick-check-msg"></span></div>
							<label for="birth" class="login-text">생년월일</label>
							<input type="text" id="birth" name="birth" class="noto-sans400" placeholder="ex)19940707" maxlength="8" value="<fmt:formatDate value="${user.birth}" pattern="yyyyMMdd" type="date" />" />
							<button type="submit" id="save-btn" class="noto-sans400">저장</button>
							<input type="hidden" name="_method" value="PATCH"/>
							<input type="hidden" name="menu" value="${param.menu}"/>
						</form>
						<input type="checkbox" style="display:none" id="nick-checkbox">
					</c:when>
					<c:when test="${param.menu eq 'password'}">
						<form id="change-form" class="noto-sans400" action="${pageContext.request.contextPath}/mypage/update" method="post" onsubmit="return formCheckPwd(this)">
							<div id="msg">${param.msg}</div>
							<label for="current-pwd" class="login-text">현재 비밀번호</label>
							<input type="password" id="current-pwd" name="current-pwd" class="noto-sans400" 
								placeholder="현재 비밀번호를 입력해 주세요" maxlength="15" />
							<label for="pwd" class="login-text">변경할 비밀번호</label>
							<input type="password" id="pwd" name="pwd" class="noto-sans400" 
								placeholder="6~15자로 입력해주세요 (영문+숫자)" maxlength="15" />
							<label for="pwd-check" class="login-text">변경할 비밀번호 확인</label>
							<input type="password" id="pwd-check" class="noto-sans400" 
								placeholder="6~15자로 입력해주세요 (영문+숫자)" maxlength="15" />
							<button type="submit" id="change-btn" class="noto-sans400">변경</button>
							<input type="hidden" name="_method" value="PATCH"/>
							<input type="hidden" name="menu" value="${param.menu}"/>
						</form>
					</c:when>
					<c:when test="${param.menu eq 'withdrawal'}">
						<form id="withdrawal-form" class="noto-sans400" action="${pageContext.request.contextPath}/mypage/withdrawal" method="post" onsubmit="return confirm('정말로 탈퇴하시겠습니까?')">
							<div id="withdrawal-text">회원탈퇴 시 작성한 게시글과 댓글은 삭제되지 않으며,<br>작성자는 탈퇴한 회원으로 표시됩니다.</div>
							<button type="submit" id="withdrawal-btn" class="noto-sans400">회원탈퇴</button>
							<input type="hidden" name="_method" value="DELETE"/>
						</form>
					</c:when>
					<c:otherwise>
						<div id="profile-cnt">
							<div>아이디</div><div>${user.id}</div>
							<div>이메일</div><div>${user.email}</div>
							<div>이름</div><div>${user.name}</div>
							<div>닉네임</div><div>${user.nickname}</div>
							<div>생일</div><div><fmt:formatDate value="${user.birth}" pattern="yyyy년 MM월 dd일" type="date" /></div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			if (${param.menu eq 'info'}) {
				$("#change-info").css("background-color", "rgba(0,0,0,0.1)");
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
								// 중복이 아닌경우에만 폼이 제출된다 (닉네임을 변경하지 않은경우 제외)
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
				
				$("#nickname").on("input", function() {
					$("#nick-check-msg").text("");
					
				});
			} else if (${param.menu eq 'password'}) {
				$("#change-password").css("background-color", "rgba(0,0,0,0.1)");
			} else if (${param.menu eq 'withdrawal'}) {
				$("#withdrawal-membership").css("background-color", "rgba(0,0,0,0.1)");
			} else {
				$("#mypage-profile").css("background-color", "rgba(0,0,0,0.1)");
			}
		});
		
		function formCheckInfo(form) {
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
			
			// 닉네임을 재입력 한 뒤 중복체크를 하지 않은경우 메시지 출력 (닉네임을 변경하지 않은 경우 제외)
			if ($("#nick-check-msg").text() == "" && "${loginNickname}" != form.nickname.value) {
				document.querySelector("#msg").innerHTML = '닉네임 중복체크를 해주세요!';
				form.nickname.select();
				return false;
			}
			
			// 닉네임이 중복인데 저장버튼을 누른경우 메시지 출력 (닉네임을 변경하지 않은 경우 제외)
			if ($("#nick-checkbox").prop("checked") == false && "${loginNickname}" != form.nickname.value) {
				document.querySelector("#msg").innerHTML = '닉네임이 중복입니다!';
				form.nickname.select();
				return false;
			}
			return true;
		}
		
		function formCheckPwd(form) {
			if (form["current-pwd"].value.length == 0) {
				document.querySelector("#msg").innerHTML = '현재 비밀번호를 입력해 주세요!';
				form["current-pwd"].select();
				return false;
			}
			
			if (form["current-pwd"].value != "${user.pwd}") {
				document.querySelector("#msg").innerHTML = '현재 비밀번호가 일치하지 않습니다!';
				form["current-pwd"].select();
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
			return true;
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
		
		function pwdCheck(pwd) {
			let regex = /^(?=.*[a-zA-z])(?=.*[0-9])[a-zA-Z0-9]+$/;
			return regex.test(pwd);
		}
	</script>
</body>
</html>
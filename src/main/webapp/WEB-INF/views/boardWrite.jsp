<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>
<c:set var="hasSessionId"
	value="${pageContext.request.getSession(false).getAttribute('id') ne null}" />
<c:set var="loginOut" value="${hasSessionId ? '로그아웃' : '로그인'}" />
<c:set var="loginOutLink"
	value="${hasSessionId ? '/ch2/login/logout' : '/ch2/login/login'}" />
<c:set var="whichBoard"
	value="${fn:split(req.getServletPath(), '/')[0] eq 'freeBoard' ? 'free' : 'question'}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>DOKKY</title>
<link rel="stylesheet"
	href="http://localhost:8080/ch2/resources/css/header.css" />
<link
	href="https://cdn.jsdelivr.net/npm/quill@2.0.0-rc.3/dist/quill.snow.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/quill@2.0.0-rc.3/dist/quill.js"></script>
<style>
#container {
	width: 800px;
	margin: auto;
}

#title {
	margin: 5px 0px;
	height: 50px;
	width: 100%;
	border: 1px solid #ccc;
	padding: 15px;
	font-size: 1.1rem;
}

#title-cnt {
	margin: 50px 0px;
}

#title-cnt div {
	margin-left: 3px;
}

#editor {
	font-size: 1rem;
}

.ql-editor {
	min-height: 300px;
}

#btn-cnt {
	display: flex;
	flex-direction: row-reverse;
	align-items: center;
	height: 100px;
	margin-top: 50px;
}

#btn-cnt>a, #write-btn {
	background-color: rgb(107, 107, 107);
	width: 80px;
	height: 50px;
	color: white;
	text-align: center;
	line-height: 50px;
	margin-left: 30px;
}

#btn-cnt>a:hover, #write-btn:hover {
	background-color: black;
}

#write-btn {
	cursor: pointer;
	border: 0;
	font-size: 1rem;
}

<c:if test ="${hasSessionId}">
#register-btn {
	display: none;
}
</c:if>
</style>
</head>
<body>
	<div id="header">
		<div id="logo">
			<a href="<c:url value='/' />">DOKKY</a>
		</div>
		<div id="menu">
			<div class="menu-board">
				<a href="<c:url value='/freeBoard/list' />">자유게시판</a>
			</div>
			<div class="menu-board">
				<a href="<c:url value='/questionBoard/list' />">질문게시판</a>
			</div>
		</div>
		<div id="login">
			<button id="login-btn" onclick="location.href='${loginOutLink}'">${loginOut}</button>
			<button id="register-btn" onclick="location.href='/ch2/register/add'">회원가입</button>
		</div>
	</div>
	<form action="<c:url value="${whichBoard eq 'free' ? '/freeBoard/write' : '/questionBoard/write'}${searchCondition.queryString}" />" method="post"
		id="container" onsubmit="return formCheck(this)">
		<div id=title-cnt>
			<div>제목</div>
			<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." value="${board.title != null ? board.title : ''}"></input>
		</div>
		<div id="editor"></div>
		<input type="hidden" id="contents" name="contents" value="${board.contents != null ? board.contents : ''}"/>
		<div id="btn-cnt">
			<a
				href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${searchCondition.queryString}" />"
				id="cancel-btn" onclick="return confirm('게시글 작성을 취소하시겠습니까?')">취소</a>
			<button id="write-btn">등록</button>
		</div>
	</form>
	<script>
      const quill = new Quill("#editor", {
        theme: "snow",
        placeholder: "내용을 입력해주세요.",
      });
      
      let ql = document.querySelector(".ql-editor");
      
      ql.addEventListener("compositionstart", (event) => {
    	  ql.classList.toggle("ql-blank", false);
      })
      
      ql.addEventListener("compositionend", (event) => {
    	  ql.classList.toggle("ql-blank", ql.innerText == "\n");
      })
      
      
	let msg = "${msg}";
	if(msg=="WRT_ERR") {
		alert("게시글을 작성하는데 실패했습니다!");
		ql.innerHTML = document.querySelector("#contents").value;
	} 
		
      function formCheck(frm) {
    	  if(document.querySelector("#title").value.length < 5) {
    		  alert("제목을 5글자 이상 작성해 주세요!");
    		  document.querySelector("#title").select();
    		  return false;
    	  }
    	  if(ql.innerText == "\n") {
    		  alert("내용을 작성해 주세요!");
    		  quill.focus();
    		  return false;
    	  }
    	  document.querySelector("#contents").value = ql.innerHTML;
    	  return true;
      }
    </script>
</body>
</html>

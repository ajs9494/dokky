<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="hasSessionId"
	value="${pageContext.request.getSession(false).getAttribute('id') ne null}" />
<%@ page session="false" %>
<!DOCTYPE html>
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
      #board-container {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        margin: auto;
        width: 800px;
        height: 100%;
      }

      .home-board {
        width: 800px;
        text-align: center;
      }

      .home-board-header {
        background-color: rgba(74, 165, 255, 0.2);
        border-radius: 20px;
        font-size: 1.5rem;
        padding: 20px;
      }

      .home-board > ul {
        list-style: none;
        padding: 10px;
      }
      
      #board-container li {
      	padding: 10px;
      	display: flex;
	    align-items: center;
	    justify-content: center;
      }

      .board-title {
        font-size: 1.2rem;
		white-space: nowrap;
	    overflow: hidden;
	    margin-right: 3px;
	    text-overflow: ellipsis;
      }
      
      .ccnt {
      	font-size: 0.9rem;
      	color: red;
      }
    </style>
  </head>
  <body>
  	<script>
  		let msg = "${msg}";
  		if (msg=="LOUT_OK") alert("로그아웃되었습니다.");
  		if (msg=="REG_OK") alert("회원가입이 완료되었습니다.");
  		if (msg=="REG_ERR") alert("회원가입이 실패했습니다! 다시 시도해주세요.");
  		if (msg=="SES_END") alert("세션이 종료되었습니다. 다시 로그인 해주세요.");
  		if (msg === "RMV_OK") alert("회원탈퇴가 정상적으로 처리되었습니다.");
  	</script>
  	<%@include file="header.jsp"%>
    <div id="board-container">
      <div id="free-board" class="home-board">
        <div class="home-board-header noto-sans700"><a href="<c:url value='/freeBoard/list' />">자유게시판</a></div>
        <ul class="noto-sans400">
          <c:forEach var="board" items="${fli}">
          	<li>
          		<a class="board-title" href="<c:url value='/freeBoard/read/${board.bno}' />">${board.title}</a>
							<c:if test="${board.ccnt > 0}">
							<span class="ccnt">[${board.ccnt}]</span>
							</c:if>
          	</li>
          </c:forEach>
        </ul>
      </div>
      <div id="question-board" class="home-board">
        <div class="home-board-header noto-sans700"><a href="<c:url value='/questionBoard/list' />">질문게시판</a></div>
        <ul class="noto-sans400">
          <c:forEach var="board" items="${qli}">
          	<li>
          		<a class="board-title" href="<c:url value='/questionBoard/read/${board.bno}' />">${board.title}</a>
							<c:if test="${board.ccnt > 0}">
							<span class="ccnt">[${board.ccnt}]</span>
							</c:if>
          	</li>
          </c:forEach>
        </ul>
      </div>
    </div>
    <script>
	    $(".board-title").each(function() {
			let title = $(this)[0];
			if(title.scrollWidth > title.clientWidth) {
				title.setAttribute("title", title.text);
			}
		})
    </script>
  </body>
</html>
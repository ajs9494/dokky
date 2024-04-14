<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="hasSessionId" value="${pageContext.request.getSession(false).getAttribute('id') ne null}"/>
<c:set var="loginOut" value="${hasSessionId ? '로그아웃' : '로그인'}"/>
<c:set var="loginOutLink" value="${hasSessionId ? '/ch2/login/logout' : '/ch2/login/login'}"/>
<!DOCTYPE html>
  <head>
    <link rel="stylesheet" href="resources/css/header.css" />
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
        background-color: rgb(192, 192, 192);
        font-size: 1.5rem;
        padding: 20px;
      }

      .home-board > ul {
        list-style: none;
        padding: 10px;
      }
      
      #board-container li {
      	padding: 10px;
      }

      .board-title {
        font-size: 1.2rem;
      }
      
      .ccnt {
      	font-size: 0.9rem;
      	color: red;
      }
      
      <c:if test="${hasSessionId}">
      	#register-btn {
      		display: none;
      	}
      </c:if>
    </style>
  </head>
  <body>
  	<script>
  		let msg = "${msg}";
  		if(msg=="LOUT_OK") alert("로그아웃되었습니다.");
  		if(msg=="REG_OK") alert("회원가입이 완료되었습니다.");
  		if(msg=="REG_ERR") alert("회원가입이 실패했습니다! 다시 시도해주세요.");
  	</script>
    <div id="header">
      <div id="logo"><a href="<c:url value='/' />">DOKKY</a></div>
      <div id="menu">
        <div class="menu-board"><a href="<c:url value='/freeBoard/list' />">자유게시판</a></div>
        <div class="menu-board"><a href="<c:url value='/questionBoard/list' />">질문게시판</a></div>
      </div>
      <div id="login">
        <button id="login-btn" onclick="location.href='${loginOutLink}'">${loginOut}</button>
        <button id="register-btn" onclick="location.href='/ch2/register/add'">회원가입</button>
      </div>
    </div>
    <div id="board-container">
      <div id="free-board" class="home-board">
        <div class="home-board-header"><a href="<c:url value='/freeBoard/list' />">자유게시판</a></div>
        <ul>
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
        <div class="home-board-header"><a href="<c:url value='/questionBoard/list' />">질문게시판</a></div>
        <ul>
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
  </body>
</html>
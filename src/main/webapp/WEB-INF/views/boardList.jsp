<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<style>
#container {
	display: flex;
	width: 1100px;
	height: 1000px;
	margin: auto;
	flex-direction: column;
	align-items: center;
}

#board-filter-cnt {
	display: flex;
	width: 100%;
	height: 50px;
	justify-content: flex-end;
	align-items: center;
	margin-bottom: 10px;
}

#page-size {
	width: 100px;
	height: 30px;
	line-height: 30px;
}

#board-title {
	background-color: rgba(74, 165, 255, 0.2);
	width: 100%;
	height: 80px;
	text-align: center;
	font-size: 2rem;
	line-height: 80px;
}

#board-list {
	width: 100%;
}

#board-list>table {
	border-collapse: collapse;
}

#board-list>table tr {
	border-bottom: 1px rgb(162, 164, 168, 0.4) solid;
}

#board-list th {
	font-weight: bold;
	padding: 8px;
}

#board-list td {
	padding: 8px;
	text-align: center;
}

#board-list>table>tbody>tr>td:nth-child(2) {
	text-align: left;
}

#board-list table {
	width: 100%;
}

.col-bno {
	width: 11%;
}

.col-title {
	width: 45%;
}

.col-writer {
	width: 15%;
}

.col-regdate {
	width: 12%;
}

.col-viewcnt {
	width: 10%;
}

.col-likecnt {
	width: 7%;
}

#write-btn-cnt {
	display: flex;
	flex-direction: row-reverse;
	width: 100%;
	margin: 10px 10px 10px 0px;
}

#write-btn {
	background-color: rgb(107, 107, 107);
	width: 100px;
	height: 30px;
	color: white;
	text-align: center;
	line-height: 30px;
}

#write-btn:hover {
	background-color: black;
}

#page-nav-cnt {
	margin: 10px auto;
	width: 500px;
	height: 50px;
}

#page-nav {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
}

#page-nav > a {
	position: relative;
	top: 1px;
}

#page-nav>ul {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	list-style: none;
}

#page-nav>ul>li {
	padding: 10px;
}

#search-cnt {
	display: flex;
	flex-direction: row;
	justify-content: center;
	width: 100%;
	height: 80px;
	padding: 20px;
}

#condition {
	border: 1px solid #ccc;
	width: 100px;
	text-align: center;
}

#search-text {
	border: 1px solid #ccc;
	border-left: 0;
	border-right: 0;
	width: 300px;
	font-size: 1rem;
	padding: 7px;
}

#search-btn {
	background-color: rgb(107, 107, 107);
	border: 1px solid #ccc;
	width: 100px;
	height: 40px;
	color: white;
	text-align: center;
	line-height: 40px;
	cursor: pointer;
}

#search-btn:hover {
	background-color: black;
}

.ccnt {
	color: red;
	font-size: 0.8rem;
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
	if(msg=="LIST_ERR") alert("게시글 목록을 불러오는데 실패했습니다!");
	if(msg=="DEL_ERR") alert("게시글을 삭제하는데 실패했습니다!");
	if(msg=="DEL_OK") alert("게시글을 삭제했습니다");
	if(msg=="READ_ERR") alert("게시글을 불러오는데 실패했습니다!");
</script>
	<div id="header">
		<div id="logo" class="noto-sans700">
			<a href="<c:url value='/' />">DOKKY</a>
		</div>
		<div id="menu" class="noto-sans700">
			<div class="menu-board">
				<a href="<c:url value='/freeBoard/list' />">자유게시판</a>
			</div>
			<div class="menu-board">
				<a href="<c:url value='/questionBoard/list' />">질문게시판</a>
			</div>
		</div>
		<div id="login">
			<button id="login-btn" class="noto-sans400" onclick="location.href='${loginOutLink}${ph.sc.queryString}&toURL=${req.getServletPath()}'">${loginOut}</button>
			<button id="register-btn" class="noto-sans400" onclick="location.href='/ch2/register/add'">회원가입</button>
		</div>
	</div>
	<div id="container">
		<div id="board-filter-cnt">
			<select id="page-size">
				<option value="10" ${"10".equals(param.pageSize) ? "selected" : ""}>10개씩 보기</option>
				<option value="30" ${"30".equals(param.pageSize) ? "selected" : ""}>30개씩 보기</option>
				<option value="50" ${"50".equals(param.pageSize) ? "selected" : ""}>50개씩 보기</option>
			</select>
		</div>
		<div id="board-title" class="noto-sans700">${whichBoard eq 'free' ? '자유게시판' : '질문게시판'}</div>
		<div id="board-list" class="noto-sans400">
			<table>
				<colgroup>
					<col class="col-bno" />
					<col class="col-title" />
					<col class="col-writer" />
					<col class="col-regdate" />
					<col class="col-viewcnt" />
					<col class="col-likecnt" />
				</colgroup>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>등록일</th>
						<th>조회수</th>
						<th>추천</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${li}">
						<tr>
							<td>${board.bno}</td>
							<td>
							<a href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/read/' : '/questionBoard/read/'}${board.bno}${ph.sc.queryString}" />">${board.title}</a>
							<c:if test="${board.ccnt > 0}">
							<span class="ccnt">[${board.ccnt}]</span>
							</c:if>
							</td>
							<td>${board.writer}</td>
							<td><c:choose>
									<c:when test="${board.regdate.time >= startOfToday}">
										<fmt:formatDate value="${board.regdate}" pattern="HH:mm"
											type="time" />
									</c:when>
									<c:when test="${board.regdate.time >= startOfThisYear}">
										<fmt:formatDate value="${board.regdate}" pattern="MM-dd"
											type="date" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"
											type="date" />
									</c:otherwise>
								</c:choose></td>
							<td>${board.viewcnt}</td>
							<td>${board.likecnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${li eq null}">
						<div style="text-align:center; font-size:1.2rem; height:200px; line-height:200px">게시물이 없습니다.</div>
					</c:if>
		</div>
		<div id="write-btn-cnt" class="noto-sans400">
			<a href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/write' : '/questionBoard/write'}${ph.sc.queryString}" />" id="write-btn">글쓰기</a>
		</div>
		<div id="page-nav-cnt" class="noto-sans400">
			<div id="page-nav">
				<c:if test="${li ne null}">
					<c:if test="${ph.showPrev}">
						<a style="margin:0 5px; font-size:1.2rem; line-height:1.2rem"
							href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${ph.sc.getQueryString(ph.beginPage-1)}" />">&lt;</a>
					</c:if>
					<ul>
						<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
						<c:choose>
							<c:when test="${i==ph.sc.page}">
								<li style="background-color:gray; border-radius:50px;"><a style="color:white;"
								href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${ph.sc.getQueryString(i)}" />">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${ph.sc.getQueryString(i)}" />">${i}</a></li>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</ul>
					<c:if test="${ph.showNext}">
						<a style="margin:0 5px; font-size:1.2rem; line-height:1.2rem"
							href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${ph.sc.getQueryString(ph.endPage+1)}" />">&gt;</a>
					</c:if>
				</c:if>
			</div>
		</div>
		<form id="search-cnt" class="noto-sans400" action="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}" />"
			method="get">
			<select id="condition" name="option" class="noto-sans400">
				<option value="TC" ${"TC".equals(param.option) ? "selected" : ""}>제목+내용</option>
				<option value="T" ${"T".equals(param.option) ? "selected" : ""}>제목</option>
				<option value="C" ${"C".equals(param.option) ? "selected" : ""}>내용</option>
				<option value="W" ${"W".equals(param.option) ? "selected" : ""}>글쓴이</option>
			</select> <input type="text" id="search-text" name="keyword" class="noto-sans400" 
				value="${param.keyword}"  />
				<input type="hidden" name="pageSize" value="${param.pageSize}">
			<button type="submit" id="search-btn" class="noto-sans400">검색</button>
		</form>
	</div>
	<script>
		document.querySelector("#page-size").onchange = function() {
			window.location.href = "<c:url value="${whichBoard eq 'free' ? '/freeBoard/list?pageSize=' : '/questionBoard/list?pageSize='}"/>" + this.value + "&option=" + "${ph.sc.option}" + "&keyword=" + "${ph.sc.keyword}";
		}
	</script>
</body>
</html>

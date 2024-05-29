<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>
<c:set var="hasSessionId"
	value="${pageContext.request.getSession(false).getAttribute('id') ne null}" />
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
<script src="https://kit.fontawesome.com/3460525fcc.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<style>
#container {
	display: flex;
	width: 1100px;
	margin: auto;
	flex-direction: column;
	align-items: center;
}

#board-filter-cnt {
	display: flex;
	width: 100%;
	height: 50px;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

#popular-false-btn, #popular-true-btn {
	display: inline-block;
	text-align: center;
	width: 100px;
	height: 40px;
	line-height: 40px;
	background-color: white;
	border: 1px solid #ccc;
}

#popular-true-btn {
	margin-right: 20px;
}

#popular-false-btn:hover, #popular-true-btn:hover {
	border: 1px solid black;
}

.popular-chosen {
	border: 1px solid black !important;
}

.category-btn {
	margin: 0 20px;
	color: rgba(0, 0, 0, 0.6);
}

.category-btn:hover {
	color: rgba(0, 0, 0, 1);
}

.category-chosen {
	color: rgba(0, 0, 0, 1);
}

#page-size {
	width: 100px;
	height: 30px;
	line-height: 30px;
	text-align: center;
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
	table-layout: fixed;
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

#board-list>table>tbody>tr>td:nth-child(3) {
	text-align: left;
	display: flex;
    align-items: center;
}

.board-title {
	white-space: nowrap;
    overflow: hidden;
    margin-right: 3px;
    text-overflow: ellipsis;
    
}

#board-list table {
	width: 100%;
}

.col-bno {
	width: 8%;
}

.col-category {
	width: 9%;
}

.col-title {
	width: 43%;
}

.col-writer {
	width: 15%;
}

.col-regdate {
	width: 12%;
}

.col-viewcnt {
	width: 8%;
}

.col-likecnt {
	width: 5%;
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
	margin-bottom: 100px;
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
	<%@include file="header.jsp"%>
	<div id="container">
		<div id="board-filter-cnt">
			<div id="filter-btn-cnt">
				<c:choose>
					<c:when test="${whichBoard eq 'free'}">
						<a id="popular-false-btn" class="noto-sans400${searchCondition.isPopular eq false ? ' popular-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1, false)}" />">전체글</a>
						<a id="popular-true-btn" class="noto-sans400${searchCondition.isPopular eq true ? ' popular-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1, true)}" />"><i class="fa-solid fa-fire" style="color: #e11919; margin-right:5px;"></i>인기글</a>
					</c:when>
					<c:otherwise>
						<a id="popular-false-btn" class="noto-sans400${searchCondition.isPopular eq false ? ' popular-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1, false)}" />">전체글</a>
						<a id="popular-true-btn" class="noto-sans400${searchCondition.isPopular eq true ? ' popular-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1, true)}" />"><i class="fa-solid fa-fire" style="color: #e11919; margin-right:5px;"></i>인기글</a>
					</c:otherwise>
				</c:choose>
				<span id="category-btn-cnt">
					<c:choose>
						<c:when test="${whichBoard eq 'free'}">
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('전체') ? ' category-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1,'전체')}" />">전체</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('수다') ? ' category-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1,'수다')}" />">수다</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('정보') ? ' category-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1,'정보')}" />">정보</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('모임') ? ' category-chosen' : ''}" href="<c:url value="/freeBoard/list${searchCondition.getQueryString(1,'모임')}" />">모임</a>
						</c:when>
						<c:otherwise>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('전체') ? ' category-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1,'전체')}" />">전체</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('개발') ? ' category-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1,'개발')}" />">개발</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('취업') ? ' category-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1,'취업')}" />">취업</a>
							<a class="noto-sans400 category-btn${searchCondition.scCategory.equals('기타') ? ' category-chosen' : ''}" href="<c:url value="/questionBoard/list${searchCondition.getQueryString(1,'기타')}" />">기타</a>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<select id="page-size" class="noto-sans400">
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
					<col class="col-category" />
					<col class="col-title" />
					<col class="col-writer" />
					<col class="col-regdate" />
					<col class="col-viewcnt" />
					<col class="col-likecnt" />
				</colgroup>
				<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
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
							<td>${board.category}</td>
							<td>
							<a class="board-title" href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/read/' : '/questionBoard/read/'}${board.bno}${ph.sc.queryString}" />">${board.title}</a>
							<c:if test="${board.ccnt > 0}">
							<span class="ccnt">[${board.ccnt}]</span>
							</c:if>
							</td>
							<td>${board.writer eq null ? '탈퇴한 회원' : board.writer}</td>
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
			<a href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/write' : '/questionBoard/write'}${searchCondition.queryString}" />" id="write-btn">글쓰기</a>
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
				<input type="hidden" name="pageSize" value="${searchCondition.pageSize}">
				<input type="hidden" name="isPopular" value="${searchCondition.isPopular}">
				<input type="hidden" name="scCategory" value="${searchCondition.scCategory}">
			<button type="submit" id="search-btn" class="noto-sans400">검색</button>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			document.querySelector("#page-size").onchange = function() {
				if(this.value=="10") window.location.href = "/ch2${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}?pageSize=10&option=${searchCondition.option}&keyword=${searchCondition.keyword}&isPopular=${searchCondition.isPopular}&scCategory=${searchCondition.scCategory}";
				if(this.value=="30") window.location.href = "/ch2${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}?pageSize=30&option=${searchCondition.option}&keyword=${searchCondition.keyword}&isPopular=${searchCondition.isPopular}&scCategory=${searchCondition.scCategory}";
				if(this.value=="50") window.location.href = "/ch2${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}?pageSize=50&option=${searchCondition.option}&keyword=${searchCondition.keyword}&isPopular=${searchCondition.isPopular}&scCategory=${searchCondition.scCategory}";
			}
			
			$(".board-title").each(function() {
				let title = $(this)[0];
				if(title.scrollWidth > title.clientWidth) {
					title.setAttribute("title", title.text);
				}
			})
		})
	</script>
</body>
</html>

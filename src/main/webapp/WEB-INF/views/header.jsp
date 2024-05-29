<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<c:set var="toURL" value="toURL=${req.getServletPath()}" />
	<c:set var="loginNickname" value="${hasSessionId ? pageContext.request.getSession(false).getAttribute('nickname') : ''}" />
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
		<c:choose>
			<c:when test="${hasSessionId}">
				<div id="login-nickname" class="noto-sans400">
					<span>${loginNickname}</span><i class="fa-solid fa-caret-down"
						style="color: #000000;"></i>
				</div>
			</c:when>
			<c:otherwise>
				<button id="login-btn" class="noto-sans400"
					onclick="location.href='${pageContext.request.contextPath}/login/login${searchCondition != null ? (searchCondition.queryString += '&') : '?'}${toURL}'">로그인</button>
				<button id="register-btn" class="noto-sans400"
					onclick="location.href='${pageContext.request.contextPath}/register/add'">회원가입</button>
			</c:otherwise>
		</c:choose>
		<div id="user-menu-box" style="display: none">
			<a id="mypage-btn" href="${pageContext.request.contextPath}/mypage/home">마이페이지</a> <a id="logout-btn"
				href="${pageContext.request.contextPath}/login/logout">로그아웃</a>
		</div>
	</div>
</div>
<script>
$(document)
.ready(
		function() {
			// 로그인을 한 경우에만 메뉴박스의 포지션계산과 이벤트걸기를 한다
			if ($("#login-nickname").length !== 0) {
				// 메뉴박스 포지션 계산
				positionUserMenuBox();

				// 창크기 변화시 메뉴박스 포지션 다시 계산
				$(window).resize(function() {
					positionUserMenuBox();
				});

				// 닉네임 클릭시 메뉴박스 보여주고 숨겨주고
				$("#login-nickname")
						.click(
								function() {
									if ($("#user-menu-box").css(
											"display") === "flex") {
										$("#user-menu-box").hide(
												"fast");
									} else {
										$("#user-menu-box").show(
												"fast");
									}
								});

				// 메뉴박스 외에 다른 부분 클릭 시 박스를 안보이게 한다
				$(document)
						.click(
								function(event) {
									let $loginNickname = $("#login-nickname");
									let $userMenuBox = $("#user-menu-box");
									if (!$userMenuBox
											.is(event.target)
											&& $userMenuBox
													.has(event.target).length === 0
											&& !$loginNickname
													.is(event.target)
											&& $loginNickname
													.has(event.target).length === 0) {
										$userMenuBox.hide("fast");
									}
								});
			}
		});

// 메뉴박스 위치 계산
function positionUserMenuBox() {
let $loginNickname = $("#login-nickname")
let $userMenuBox = $("#user-menu-box");
let position = $loginNickname.position();

// 메뉴박스가 화면을 벗어나 오른쪽부분이 가려지는 경우 위치조정
if (position.left + $loginNickname.outerWidth() > $(window).width()) {
$userMenuBox.css({
	top : position.top + $loginNickname.outerHeight(),
	right : 0
});
} else {
$userMenuBox.css({
	top : position.top + $loginNickname.outerHeight(),
	right : $(window).width() - (position.left + $loginNickname.outerWidth())
});
}
}
</script>
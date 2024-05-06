<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<c:set var="hasSessionId" value="${pageContext.request.getSession(false).getAttribute('id') ne null}"/>
<c:set var="loginOut" value="${hasSessionId ? '로그아웃' : '로그인'}"/>
<c:set var="loginOutLink" value="${hasSessionId ? '/ch2/login/logout' : '/ch2/login/login'}"/>
<c:set var="whichBoard"
	value="${fn:split(req.getServletPath(), '/')[0] eq 'freeBoard' ? 'free' : 'question'}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DOKKY</title>
    <link rel="stylesheet" href="http://localhost:8080/ch2/resources/css/header.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/3460525fcc.js" crossorigin="anonymous"></script>
    <style>
      #container {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 1000px;
        margin: auto;
        padding: 10px;
      }

      #board-container {
        width: 100%;
      }

      #comment-container {
        width: 100%;
      }

      #board-header {
      	display: grid;
      	grid-template-columns: 1fr 1fr;
      	grid-template-rows: 1fr 1fr;
        margin-bottom: 50px;
        border-bottom: 1px solid rgba(0,0,0,0.2);
        background-color: rgba(0,0,0,0.04);
        padding: 0 10px;
      }

      #board-header-writer {
        font-size: 1.2rem;
      }
      
      #board-name, #board-category {
      	text-align: right;
      }
      
      #board-title {
      	margin-bottom: 70px;
      }

      #board-contents {
        min-height: 100px;
        margin-bottom: 50px;
      }
      
      #board-contents a {
      	color: deepskyblue;
      	text-decoration: underline;
      	text-decoration-color: deepskyblue;
      }
      
      #board-like-cnt {
      	display: flex;
      	justify-content: center;
      	align-items: center;
      	width: 100%;
      	height: 70px;
      	margin-bottom: 50px;
      }
      
      #board-like-btn, #board-dislike-btn {
      	width: 110px;
      	height: 60px;
      	margin: 0 15px;
      	background-color: white;
      	font-size: 1.5rem;
      	cursor: pointer;
      	border: 1px solid #ccc;
      }
      
      #board-like-btn i {
      	color: rgba(255, 0, 0, 0.8);
      }
      
      #board-dislike-btn i {
      	color: rgba(0, 157, 255, 0.6)
      }
      
      #board-like-cnt i {
      	margin-right: 10px;
      }
      
      .like-chosen {
      	color: white !important;
      	background-color: #ccc !important;
      }

      #board-btn-cnt {
        display: flex;
        flex-direction: row-reverse;
        margin-bottom: 150px;
      }

      #board-btn-cnt button, #board-btn-cnt a {
        background-color: rgb(107, 107, 107);
        border: 1px solid #ccc;
        width: 100px;
        height: 30px;
        color: white;
        text-align: center;
        margin-left: 10px;
        cursor: pointer;
        font-size: 1rem;
      }
      
      #board-btn-cnt a {
      	line-height: 28px;
      }

      #board-btn-cnt button:hover, #board-btn-cnt a:hover {
        background-color: black;
      }
      
      #comment-container {
      	margin-bottom: 100px;
      }

      #comment-container ul {
        list-style: none;
      }

      #comment-count {
        height: 70px;
        line-height: 70px;
        font-size: 1.2rem;
      }

      .comment {
        padding: 10px 10px;
        border-top: 1px solid #ccc;
      }
      
      .comment-writer-like-cnt {
      	display: flex;
      	justify-content: space-between;
      }
      
      .comment-like-cnt i {
      	margin-right: 5px;
      }
      
      .comment-like-btn, .comment-dislike-btn {
      	width: 50px;
      	height: 30px;
      	margin: 0 5px;
      	background-color: white;
      	font-size: 1.1rem;
      	cursor: pointer;
      	border: 1px solid #ccc;
      	padding: 5px;
      }
      
      .comment-like-btn i {
      	color: rgba(255, 0, 0, 0.8);
      }
      
      .comment-dislike-btn i {
      	color: rgba(0, 157, 255, 0.6)
      }
     
      .comment-rep {
      	margin-left: 30px;
      	padding-left: 20px;
      	border-left: 1px solid #ccc;
      	border-top: 0;
      	margin-bottom: 20px;
      	background-color: rgba(0,0,0,0.05);
      }

      .comment-regdate {
        margin-bottom: 10px;
      }

      .comment-btn-cnt {
        display: flex;
        flex-direction: row-reverse;
        font-size: 0.8rem;
        height: 17px;
        margin-right: 10px;
      }

      .comment-btn-cnt > a {
        margin-left: 10px;
        cursor: pointer;
      }

      #comment-add-cnt {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 150px;
      }

      #comment-add-contents {
        height: 70%;
        width: 70%;
        padding: 15px;
        border: 1px solid #ccc;
        border-right: 0;
        resize: none;
        font-size: 1.0rem;
      }

      #comment-add-btn {
        background-color: white;
        border: 1px solid #ccc;
        width: 70px;
        height: 105px;
        line-height: 105px;
        text-align: center;
        font-weight: bold;
        cursor: pointer;
      }
      
      #rep-cnt {
        display: none;
        justify-content: center;
        align-items: center;
        height: 100px;
      }
      
      #rep-contents {
        height: 70%;
        width: 70%;
        padding: 10px;
        border: 1px solid #ccc;
        border-right: 0;
        resize: none;
        font-size: 0.8rem;
      }

      #rep-add-btn {
        background-color: white;
        border: 1px solid #ccc;
        width: 60px;
        height: 70px;
        line-height: 70px;
        text-align: center;
        font-weight: bold;
        cursor: pointer;
      }
      
      #modal {
      	display: none;
      	position: fixed;
      	left: 0;
      	top: 0;
      	z-index: 1;
      	width: 100%;
      	height: 100%;
      	background-color: rgba(0,0,0,0.4);
      }
      
      #modal-content {
      	background-color: white;
      	position: fixed;
      	left: 50%;
      	top: 40%;
      	transform: translate(-50%, -50%);
      }
      
      .close-icon {
      	font-size: 3rem;
      	height: 34px;
      	line-height: 23px;
      	font-weight: bold;
      	float: right;
      	cursor: pointer;
      	color:white;
      	background-color: black;
      	position: relative;
      	left: 20px;
      	top: -20px;
      	border-radius: 30px;
      }
      
      #mod-cnt {
      	padding: 20px;
      }
      
      #mod-contents {
       	width: 680px;
       	height: 105px;
      	border: 1px solid #ccc;
      	resize: none;
        font-size: 1.0rem;
        padding: 15px;
        margin: 10px 0;
      }
      
      #mod-btn-cnt {
      	display: flex;
      	justify-content: space-evenly;
      	margin-top: 20px;
      }
      
      #mod-btn {
      	width:30%;
      	height: 40px;
      	line-height: 40px;
      	cursor: pointer;
      	text-align: center;
      	background-color: rgba(0, 170, 255, 0.81);
      	color: white;
      }
      
      #mod-btn:hover {
      	background-color: rgb(0, 140, 255);
      }
      
      .close-btn {
      	width:30%;
      	cursor: pointer;
      	height: 40px;
      	line-height: 40px;
      	text-align: center;
      	background-color: rgba(0, 0, 17, 0.2);
      	color: white;
      }
      
      .close-btn:hover {
      	background-color: rgba(0, 0, 17, 0.4);
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
  	if(msg=="WRT_OK") alert("게시글이 등록되었습니다");
  	if(msg=="UPD_OK") alert("게시글이 수정되었습니다");
  </script>
    <div id="header">
      <div id="logo" class="noto-sans700"><a href="<c:url value='/' />">DOKKY</a></div>
      <div id="menu" class="noto-sans700">
        <div class="menu-board"><a href="<c:url value='/freeBoard/list' />">자유게시판</a></div>
        <div class="menu-board"><a href="<c:url value='/questionBoard/list' />">질문게시판</a></div>
      </div>
      <div id="login">
        <button id="login-btn" class="noto-sans400" onclick="location.href='${loginOutLink}${searchCondition.queryString}&toURL=${req.getServletPath()}'">${loginOut}</button>
        <button id="register-btn" class="noto-sans400" onclick="location.href='/ch2/register/add'">회원가입</button>
      </div>
    </div>
    <div id="container" class="noto-sans400">
      <div id="board-container">
        <div id="board-header">
          <div id="board-header-writer">${board.writer}</div>
          <div id="board-name">${whichBoard eq 'free' ? '자유게시판' : '질문게시판'}</div>
          <div id="board-header-regview"><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" type="both" /> / ${board.viewcnt}</div>
          <div id="board-category">${board.category}</div>
        </div>
        <h1 id="board-title">${board.title}</h1>
        <div id="board-contents">${board.contents}</div>
        <div id="board-like-cnt">
        	<button type="button" id="board-like-btn" class="noto-sans400"></button>
        	<button type="button" id="board-dislike-btn" class="noto-sans400"></button>
        </div>
        <div id="board-btn-cnt">
        	<c:if test="${isWriter==true}">
          		<form action="<c:url value="${whichBoard eq 'free' ? '/freeBoard/update' : '/questionBoard/update'}" />" method="post" onsubmit="return confirm('게시글을 수정하시겠습니까?')">
          		<input type="hidden" name="page" value="${searchCondition.page}"/>
          		<input type="hidden" name="pageSize" value="${searchCondition.pageSize}"/>
          		<input type="hidden" name="option" value="${searchCondition.option}"/>
          		<input type="hidden" name="keyword" value="${searchCondition.keyword}"/>
          		<input type="hidden" name="isPopular" value="${searchCondition.isPopular}"/>
          		<input type="hidden" name="scCategory" value="${searchCondition.scCategory}"/>
          		<input type="hidden" name="bno" value="${board.bno}"/>
          		<input type="hidden" name="title" value="${board.title}"/>
          		<input type="hidden" name="contents" value="<c:out value='${board.contents}'/>"/>
          		<input type="hidden" name="category" value="${board.category}"/>
          		<button class="noto-sans400">수정</button></form>
          		<form action="<c:url value="${whichBoard eq 'free' ? '/freeBoard/remove/' : '/questionBoard/remove/'}${board.bno}${searchCondition.queryString}" />" method="post" onsubmit="return confirm('게시글을 삭제하시겠습니까?')"><input type="hidden" name="_method" value="DELETE"/><button class="noto-sans400">삭제</button></form>
          	</c:if>
          <a href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/list' : '/questionBoard/list'}${searchCondition.queryString}" />">목록</a>
        </div>
      </div>
      <div id="comment-container">
      <div id="comment-box">
      	<div id="comment-count">${commentsCount}개의 댓글</div>
        <ul>
        <c:forEach var="comment" items="${comments}">
          <li class="${comment.cno == comment.pcno ? 'comment' : 'comment comment-rep'}" data-cno="${comment.cno}" data-pcno="${comment.pcno}">
          	<div class="comment-writer-like-cnt"><div class="comment-writer">${comment.writer}</div>
          	<div class="comment-like-cnt"><button type="button" class="comment-like-btn"></button><button type="button" class="comment-dislike-btn"></button></div>
          	</div>
            <div class="comment-regdate"><fmt:formatDate value="${comment.regdate}" pattern="yyyy-MM-dd HH:mm" type="both"/></div>
            <div class="comment-contents">${comment.contents}</div>
            <div class="comment-btn-cnt">
            <c:if test="${nickname eq comment.writer}">
              <a class="comment-mod-btn">수정</a>
              <a class="comment-del-btn">삭제</a>
            </c:if>
            <c:if test="${hasSessionId && comment.cno == comment.pcno}">
              <a class="comment-rep-btn">답글</a>
            </c:if>
            </div>
          </li>
          </c:forEach>
        </ul>
      </div>
        <div id="comment-add-cnt">
          <textarea
            id="comment-add-contents"
            name="contents"
            class="noto-sans400"
            maxlength="1000"
            spellcheck="false"
            placeholder="${hasSessionId ? '댓글을 작성해 보세요.' : '로그인 후에 댓글을 작성해 주세요.'}"
            ${hasSessionId ? "" : "disabled"}
          ></textarea>
          <button type="button" id="comment-add-btn" class="noto-sans400" ${hasSessionId ? "" : "disabled"}>등록</button>
        </div>
            <div id="rep-cnt">
        	<textarea id="rep-contents" class="noto-sans400" maxlength="1000" spellcheck="false" placeholder="답글을 작성해 주세요."></textarea>
        	<button type="button" id="rep-add-btn" class="noto-sans400">등록</button>
        </div>
      </div>
    </div>
    <div id="modal">
    	<div id="modal-content">
    		<span class="close close-icon">&times</span>
    		<div id="mod-cnt" class="noto-sans400">
    			<h2>댓글 수정</h2>
    			<input type="hidden" id="mod-cno"/>
    			<input type="hidden" id="mod-writer"/>
    			<textarea id="mod-contents" class="noto-sans400" maxlength="1000" spellcheck="false" placeholder="수정할 내용을 작성해 주세요."></textarea>
    			<div id="mod-btn-cnt">
    				<span id="mod-btn">등록</span>
    				<span class="close close-btn">취소</span>
    			</div>
    		</div>
    	</div>
    </div>
    <script>
    	$(document).ready(function() {
    		// 게시글 좋아요 보여주기
    		$.ajax({
				type: 'GET',
				url: '/ch2${whichBoard eq 'free' ? '/freeBoardLike/show/' : '/questionBoardLike/show/'}${board.bno}',
				headers: { "content-type": "application/json" },
				success: function(result) {
					$("#board-like-btn").html('<i class="fa-regular fa-thumbs-up"></i>' + result.likeCnt);
					$("#board-dislike-btn").html('<i class="fa-regular fa-thumbs-down"></i>' + result.dislikeCnt);
					if(result.isLiked != undefined) {
						if(result.isLiked == true) {
							$("#board-like-btn").addClass("like-chosen");
						} else {
							$("#board-dislike-btn").addClass("like-chosen");
						}
					}
				},
				error: function() {
					alert("추천, 비추천수를 불러오는데 실패했습니다!");
				}
			})
			
			// 댓글 좋아요 보여주기
			showCommentLike();
    		
    		$("#comment-add-btn").click(function() {
    			if(commentContentsCheck($("#comment-add-contents").val())) {
	    			writeComment();
    			} else alert("댓글 내용을 작성해 주세요!");
    		})
    		
    		$("#comment-box").on("click", ".comment-del-btn", function(e) {
    			if(confirm("댓글을 삭제하시겠습니까?")) {
    				let cno = $(e.target).closest(".comment").data("cno");
    				let pcno = $(e.target).closest(".comment").data("pcno");
        			let writer = $(e.target).parents(".comment").find(".comment-writer").text();
        			removeComment(cno, pcno, writer, ${board.bno});
    			}
    		})
    		
    		$("#comment-box").on("click", ".comment-mod-btn", function(e) {
    			let cno = $(e.target).closest(".comment").data("cno");
    			let writer = $(e.target).parents(".comment").find(".comment-writer").text();
    			let contents = $(e.target).parents(".comment").find(".comment-contents").text();
    			$("#mod-cno").val(cno);
    			$("#mod-writer").val(writer);
    			$("#mod-contents").val(contents);
    			$("#modal").css("display", "block");
    		})
    		
    		$(".close").click(function() {
    			$("#modal").css("display", "none");
    		})
    		
    		$("#mod-btn").click(function() {
    			if(commentContentsCheck($("#mod-contents").val())) {
    				let cno = $("#mod-cno").val();
    				let writer = $("#mod-writer").val();
    				let contents = $("#mod-contents").val();
    				updateComment(cno, writer, contents);
    				$("#modal").css("display", "none");
    			} else alert("수정할 내용을 작성해 주세요!");
    		})
    		
    		$("#comment-box").on("click", ".comment-rep-btn", function(e) {
    			// 답글버튼 눌렀을때 해당댓글에 답글폼이 이미 존재하면, 답글폼을 안보이게한다
    			if($(this).parent().next("#rep-cnt").length == 1) {
    				$("#rep-contents").val("");
        			$("#rep-cnt").css("display", "none");
        			// 답글폼이 숨겨졌지만, 존재함, 폼이 나타나지 않는 것을 방지하기위해 다른곳으로 옮겨준다
        			$("#rep-cnt").appendTo($("#comment-container"));
    			} else {
    				$("#rep-contents").val("");
    				$("#rep-cnt").css("display", "flex");
    				$("#rep-cnt").appendTo($(this).parents(".comment"));
    			}
    		})
    		
    		$("#comment-box").on("click", "#rep-add-btn", function(e) {
    			if(commentContentsCheck($("#rep-contents").val())) {
    			let pcno = $(e.target).closest(".comment").data("pcno");
    			writeCommentRep(pcno);
    			} else {
    				alert("답글 내용을 작성해 주세요!");
    			}
    		})
    		
    		$("#board-like-btn").click(function() {
    			// 로그인 안되어 있으면 메시지 보여줌
    			if(${hasSessionId}) {
    				// 해당 글 작성자가 로그인한 본인이라면 추천 불가
    				if(${isWriter==false}) {
	    				// 사용자가 추천을 이미 누른 상태면 해당버튼에 추가되는 클래스인 like-chosen클래스가 있다면 추천을 취소
	    				if($(this).hasClass("like-chosen")) {
	    					// true가 추천, false가 비추천
	    					removeLike(true);
	    					// 사용자가 비추천을 이미 누른 상태면 메시지 보여줌
	    				} else if($(this).siblings().hasClass("like-chosen")) {
	    					alert("이미 비추천한 상태입니다!");
	    				} else {
		    				addLike(true);
	    				}
    				} else {
    					alert("본인의 게시글을 추천할 수 없습니다!");
    				}
    			} else {
    				alert("로그인 후에 이용할 수 있습니다!");
    			}
    		})
    		
    		$("#board-dislike-btn").click(function() {
    			// 로그인 안되어 있으면 메시지 보여줌
    			if(${hasSessionId}) {
    				// 해당 글 작성자가 로그인한 본인이라면 비추천 불가
    				if(${isWriter==false}) {
	    				// 사용자가 비추천을 이미 누른 상태면 해당버튼에 추가되는 클래스인 like-chosen클래스가 있다면 비추천을 취소
	    				if($(this).hasClass("like-chosen")) {
	    					// true가 추천, false가 비추천
	    					removeLike(false);
	    					// 사용자가 추천을 이미 누른 상태면 메시지 보여줌
	    				} else if($(this).siblings().hasClass("like-chosen")) {
	    					alert("이미 추천한 상태입니다!");
	    				} else {
		    				addLike(false);
	    				}
    				} else {
    					alert("본인의 게시글을 비추천할 수 없습니다!");
    				}
    			} else {
    				alert("로그인 후에 이용할 수 있습니다!");
    			}
    		})
    		
    		$("#comment-box").on("click", ".comment-like-btn", function(e) {
    			let cno = $(e.target).closest(".comment").data("cno");
    			let writer = $(e.target).closest(".comment").find(".comment-writer").text();
    			let nickname = "${nickname}";
    			// 로그인 안되어 있으면 메시지 보여줌
    			if(${hasSessionId}) {
    				// 클릭된 댓글의 작성자가 로그인한 본인이면 추천 불가
    				if(writer != nickname) {
	    				// 사용자가 추천을 이미 누른 상태면 해당버튼에 추가되는 클래스인 like-chosen클래스가 있다면 추천을 취소
	    				if($(this).hasClass("like-chosen")) {
	    					removeCommentLike(cno);
	    					// 사용자가 비추천을 이미 누른 상태면 메시지 보여줌
	    				} else if($(this).siblings().hasClass("like-chosen")) {
	    					alert("이미 비추천한 상태입니다!");
	    				} else {
	    					// true가 추천, false가 비추천
	    					addCommentLike(cno, true);
	    				}
    				} else {
    					alert("본인의 댓글을 추천할 수 없습니다!");
    				}
    			} else {
    				alert("로그인 후에 이용할 수 있습니다!");
    			}
    		})
    		
    		$("#comment-box").on("click", ".comment-dislike-btn", function(e) {
    			let cno = $(e.target).closest(".comment").data("cno");
    			let writer = $(e.target).closest(".comment").find(".comment-writer").text();
    			let nickname = "${nickname}";
    			// 로그인 안되어 있으면 메시지 보여줌
    			if(${hasSessionId}) {
    				// 클릭된 댓글의 작성자가 로그인한 본인이면 비추천 불가
    				if(writer != nickname) {
	    				// 사용자가 비추천을 이미 누른 상태면 해당버튼에 추가되는 클래스인 like-chosen클래스가 있다면 비추천을 취소
	    				if($(this).hasClass("like-chosen")) {
	    					removeCommentLike(cno);
	    					// 사용자가 추천을 이미 누른 상태면 메시지 보여줌
	    				} else if($(this).siblings().hasClass("like-chosen")) {
	    					alert("이미 추천한 상태입니다!");
	    				} else {
	    					// true가 추천, false가 비추천
	    					addCommentLike(cno, false);
	    				}
    				} else {
    					alert("본인의 댓글을 비추천할 수 없습니다!");
    				}
    			} else {
    				alert("로그인 후에 이용할 수 있습니다!");
    			}
    		})
    	})
    	
    	let writeComment = function() {
    		let comment = { contents: $("#comment-add-contents").val() };
			$.ajax({
				type: 'POST',
				url: '/ch2${whichBoard eq 'free' ? '/freeComment/write/' : '/questionComment/write/'}${board.bno}',
				headers: { "content-type": "application/json" },
				data: JSON.stringify(comment),
				success: function(result) {
					$("#comment-box").html(toHtml(result));
					$("#comment-add-contents").val("");
					showCommentLike();
				},
				error: function() {
					alert("댓글 등록에 실패했습니다! 다시 시도해 주세요!");
				}
			})
    	}
    	
    	let writeCommentRep = function(pcno) {
    		let comment = {
    				pcno: pcno,
    				contents: $("#rep-contents").val()
    		}
    		$.ajax({
				type: 'POST',
				url: '/ch2${whichBoard eq 'free' ? '/freeComment/write/' : '/questionComment/write/'}${board.bno}',
				headers: { "content-type": "application/json" },
				data: JSON.stringify(comment),
				success: function(result) {
					$("#comment-box").html(toHtml(result));
					showCommentLike();
				},
				error: function() {
					alert("답글 등록에 실패했습니다! 다시 시도해 주세요!");
				}
			})
    	}
    	
    	let removeComment = function(cno, pcno, writer, bno) {
    		$.ajax({
				type: 'DELETE',
				url: '/ch2${whichBoard eq 'free' ? '/freeComment/remove/' : '/questionComment/remove/'}'+cno+'?writer='+writer+'&bno='+bno+'&pcno='+pcno,
				headers: { "content-type": "application/json" },
				success: function(result) {
					$("#comment-box").html(toHtml(result));
					showCommentLike();
				},
				error: function() {
					alert("댓글 삭제에 실패했습니다! 다시 시도해 주세요!");
				}
			})
    	}
    	
    	let updateComment = function(cno, writer, contents) {
    		let comment = {
    				cno: cno,
    				writer: writer,
    				contents: contents,
    				bno: ${board.bno}
    		}
    		$.ajax({
				type: 'PATCH',
				url: '/ch2${whichBoard eq 'free' ? '/freeComment/update/' : '/questionComment/update/'}'+cno,
				headers: { "content-type": "application/json" },
				data: JSON.stringify(comment),
				success: function(result) {
					$("#comment-box").html(toHtml(result));
					showCommentLike();
				},
				error: function() {
					alert("댓글 수정에 실패했습니다! 다시 시도해 주세요!");
				}
			})
    	}
    	
    	let addLike = function(isLiked) {
    		let like = {
    				isLiked: isLiked,
    		}
    		$.ajax({
				type: 'POST',
				url: '/ch2${whichBoard eq 'free' ? '/freeBoardLike/add/' : '/questionBoardLike/add/'}${board.bno}',
				headers: { "content-type": "application/json" },
				data: JSON.stringify(like),
				success: function(result) {
					$("#board-like-btn").html('<i class="fa-regular fa-thumbs-up"></i>' + result.likeCnt);
					$("#board-dislike-btn").html('<i class="fa-regular fa-thumbs-down"></i>' + result.dislikeCnt);
					if(result.isLiked != undefined) {
						if(result.isLiked == true) {
							$("#board-like-btn").addClass("like-chosen");
						} else {
							$("#board-dislike-btn").addClass("like-chosen");
						}
					}
				},
				error: function() {
					alert("추천 또는 비추천 등록에 실패했습니다!");
				}
			})
    	}
    	
    	let removeLike = function(isLikeBtn) {
    		let like = {
    				isLiked: isLikeBtn,
    		}
    		$.ajax({
				type: 'DELETE',
				url: '/ch2${whichBoard eq 'free' ? '/freeBoardLike/remove/' : '/questionBoardLike/remove/'}${board.bno}',
				headers: { "content-type": "application/json" },
				data: JSON.stringify(like),
				success: function(result) {
					$("#board-like-btn").html('<i class="fa-regular fa-thumbs-up"></i>' + result.likeCnt);
					$("#board-dislike-btn").html('<i class="fa-regular fa-thumbs-down"></i>' + result.dislikeCnt);
					if(isLikeBtn) {
						$("#board-like-btn").removeClass("like-chosen");
					} else {
						$("#board-dislike-btn").removeClass("like-chosen");
					}
				},
				error: function() {
					alert("추천 또는 비추천 취소에 실패했습니다!");
				}
			})
    	}
    	
    	let showCommentLike = function() {
    		$.ajax({
				type: 'GET',
				url: '/ch2${whichBoard eq 'free' ? '/freeCommentLike/show/' : '/questionCommentLike/show/'}${board.bno}',
				headers: { "content-type": "application/json" },
				success: function(result) {
					result.forEach(function(like, index) {
					$("#comment-box > ul > li:nth-child(" + (index + 1) + ") .comment-like-cnt").html('<button type="button" class="comment-like-btn"><i class="fa-regular fa-thumbs-up"></i>' + like.likeCnt + '</button><button type="button" class="comment-dislike-btn"><i class="fa-regular fa-thumbs-down"></i>' + like.dislikeCnt + '</button>');
					if(like.isLiked != undefined) {
						if(like.isLiked == true) {
							$("#comment-box > ul > li:nth-child(" + (index + 1) + ") .comment-like-btn").addClass("like-chosen");
						} else {
							$("#comment-box > ul > li:nth-child(" + (index + 1) + ") .comment-dislike-btn").addClass("like-chosen");
						}
					}
					})
				},
				error: function() {
					alert("추천, 비추천수를 불러오는데 실패했습니다!");
				}
			})
    	}
    	
    	let addCommentLike = function(cno, isLiked) {
    		let like = {
    				cno: cno,
    				isLiked: isLiked,
    		}
    		$.ajax({
				type: 'POST',
				url: '/ch2${whichBoard eq 'free' ? '/freeCommentLike/add/' : '/questionCommentLike/add/'}' + cno,
				headers: { "content-type": "application/json" },
				data: JSON.stringify(like),
				success: function(result) {
					showCommentLike();
				},
				error: function() {
					alert("추천 또는 비추천 등록에 실패했습니다!");
					showCommentLike();
				}
			})
    	}
    	
    	let removeCommentLike = function(cno) {
    		let like = {
    				cno: cno
    		}
    		$.ajax({
				type: 'DELETE',
				url: '/ch2${whichBoard eq 'free' ? '/freeCommentLike/remove/' : '/questionCommentLike/remove/'}' + cno,
				headers: { "content-type": "application/json" },
				data: JSON.stringify(like),
				success: function(result) {
					showCommentLike();
				},
				error: function() {
					alert("추천 또는 비추천 취소에 실패했습니다!");
					showCommentLike();
				}
			})
    	}
    	
    	let toHtml = function(comments) {
    		let tmp = '<div id="comment-count">' + comments.length + '개의 댓글</div>' + '<ul>';
    		
    		comments.forEach(function(comment) {
    			// 날짜형식
    			let d = new Date(comment.regdate);
    			let year = d.getFullYear();
    			let month = d.getMonth()+1;
    			let day = d.getDate();
    			if (month < 10) 
    		        month = '0' + month;
    		    if (day < 10) 
    		        day = '0' + day;
    			let date = [year, month, day].join('-');
    			let hours = d.getHours();
    			let minutes = d.getMinutes();
    			if (hours < 10) 
    				hours = '0' + hours;
    		    if (minutes < 10) 
    		    	minutes = '0' + minutes;
    		    let time = [hours, minutes].join(':');
    		    let dateTime = [date, time].join(' ');
    		    
    		    // 모델에 저장된 로그인 닉네임 가져오기
    		    let nickname = "${nickname}";
    		    
    		    // html태그 생성
    			tmp += '<li class='
    			// 답글 css적용을 위해 댓글인지 답글인지에 따라 클래스 다르게 부여 
    			if(comment.cno == comment.pcno) {
    				tmp += '"comment"';
    			} else {
    				tmp += '"comment comment-rep"';
    			}
    			tmp += ' data-cno=' + comment.cno;
    			tmp += ' data-pcno=' + comment.pcno + '>';
    			tmp += '<div class="comment-writer-like-cnt">';
    			tmp += '<div class="comment-writer">' + comment.writer + '</div>';
    			tmp += '<div class="comment-like-cnt"><button type="button" class="comment-like-btn"></button><button type="button" class="comment-dislike-btn"></button></div></div>';
    			tmp += '<div class="comment-regdate">' + dateTime + '</div>';
    			tmp += '<div class="comment-contents">' + comment.contents + '</div>';
    			tmp += '<div class="comment-btn-cnt">';
    			// 로그인 닉네임과 댓글 작성자 같은지 확인해서 수정삭제버튼을 보여줄지 말지 결정
    			if(nickname==comment.writer) tmp += '<a class="comment-mod-btn">수정</a><a class="comment-del-btn">삭제</a>';
    			// 댓글만 답글버튼 만들고 답글이면 답글버튼 안만들기
    			if(comment.cno == comment.pcno) tmp += '<a class="comment-rep-btn">답글</a>';
    			tmp += '</div></li>';
    		})
    			if($("#rep-cnt").css("display")=="flex") {
    				$("#comment-container").append('<div id="rep-cnt"><textarea id="rep-contents" maxlength="1000" spellcheck="false" placeholder="답글을 작성해 주세요."></textarea><button type="button" id="rep-add-btn">등록</button></div>');
    			}
    		
    		return tmp + '</ul>';
    	}
    	
    	let commentContentsCheck = function(contents) {
    		return contents != "";
    	}
    </script>
  </body>
</html>


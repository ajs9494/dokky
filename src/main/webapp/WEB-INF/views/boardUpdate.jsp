<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/quill@2.0.0-rc.3/dist/quill.snow.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/quill@2.0.0-rc.3/dist/quill.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/3460525fcc.js"
	crossorigin="anonymous"></script>
<style>
#container {
	width: 800px;
	margin: auto;
}

#write-header {
	font-size: 1.5rem;
	border-bottom: 2px solid black;
	padding-bottom: 10px;
}

#category-cnt {
	margin-top: 50px;
}

#category-cnt div {
	margin-left: 3px;
}

#category {
	width: 100%;
	height: 50px;
	margin: 5px 0px;
	border: 1px solid #ccc;
	padding: 10px;
	font-size: 1rem;
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
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
}

.ql-editor .ql-video {
	width: 100%;
	height: 432px;
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

#loading-cnt {
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	z-index: 1;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

#loading-cnt i {
	position: fixed;
	left: 50%;
	top: 40%;
	transform: translate(-50%, -50%);
	font-size: 8rem;
	color: white;
}

#image-select-cnt {
	display: flex;
	position: absolute;
	width: 300px;
	height: 140px;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	flex-direction: column;
	justify-content: space-around;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
	background-color: white;
}

#file-select-btn {
	width: 100%;
	margin-bottom: 5px;
	cursor: pointer;
	padding: 5px;
}

#image-url-input {
	display: inline-block;
	width: 78%;
	padding: 5px;
}

#url-submit-btn {
	width: 20%;
	cursor: pointer;
	padding: 5px;
	background-color: rgb(107, 107, 107);
	color: white;
	border: 1px solid rgb(107, 107, 107);
}

#url-submit-btn:hover {
	background-color: black;
	border: 1px solid black;
}

#image-cancel-btn {
	width: 100%;
	margin-top: 5px;
	cursor: pointer;
	padding: 5px;
	border: 1px solid #ccc;
	background-color: white;
}

#image-cancel-btn:hover {
	background-color: rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
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
			<button id="login-btn" class="noto-sans400"
				onclick="location.href='${loginOutLink}'">${loginOut}</button>
			<button id="register-btn" class="noto-sans400"
				onclick="location.href='/ch2/register/add'">회원가입</button>
		</div>
	</div>
	<form
		action="<c:url value="${whichBoard eq 'free' ? '/freeBoard/update/' : '/questionBoard/update/'}${board.bno}${searchCondition.queryString}" />"
		method="post" id="container" class="noto-sans400"
		onsubmit="return formCheck(event)">
		<input type="hidden" name="_method" value="PATCH" />
		<div id="write-header" class="noto-sans700">${whichBoard eq 'free' ? '자유게시판 글수정' : '질문게시판 글수정'}</div>
		<div id="category-cnt">
			<div>카테고리</div>
			<select id="category" name="category" class="noto-sans400">
				<c:choose>
					<c:when test="${whichBoard eq 'free'}">
						<option value="수다" ${board.category eq "수다" ? "selected" : ""}>수다</option>
						<option value="정보" ${board.category eq "정보" ? "selected" : ""}>정보</option>
						<option value="모임" ${board.category eq "모임" ? "selected" : ""}>모임</option>
					</c:when>
					<c:otherwise>
						<option value="개발" ${board.category eq "개발" ? "selected" : ""}>개발</option>
						<option value="취업" ${board.category eq "취업" ? "selected" : ""}>취업</option>
						<option value="기타" ${board.category eq "기타" ? "selected" : ""}>기타</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div id=title-cnt>
			<div>제목</div>
			<input type="text" id="title" class="noto-sans400" name="title"
				placeholder="제목을 입력해주세요." value="${board.title}"></input>
		</div>
		<div id="editor"></div>
		<input type="hidden" id="contents" name="contents"
			value="<c:out value="${board.contents}"/>" />
		<div id="btn-cnt">
			<a
				href="<c:url value="${whichBoard eq 'free' ? '/freeBoard/read/' : '/questionBoard/read/'}${board.bno}${searchCondition.queryString}" />"
				id="cancel-btn" onclick="return confirm('게시글 수정을 취소하시겠습니까?')">취소</a>
			<button id="write-btn" class="noto-sans400">수정</button>
		</div>
	</form>
	<div id="loading-cnt">
		<i class="fa-solid fa-spinner fa-spin-pulse"></i>
	</div>
	<div id="image-select-cnt" style="display: none">
		<button type="button" id="file-select-btn" class="noto-sans400">파일
			선택</button>
		<div>
			<input type="text" id="image-url-input" placeholder="이미지 URL을 입력해주세요"
				class="noto-sans400" />
			<button type="button" id="url-submit-btn" class="noto-sans400">입력</button>
		</div>
		<button type="button" id="image-cancel-btn" class="noto-sans400">취소</button>
	</div>
	<script>
	$(document).ready(function() {
		positionSelectImage();
		
		// 로그인상태면 헤더에 회원가입버튼을 숨긴다
		if (${hasSessionId == true}) {
			$("#register-btn").css("display", "none");
		}
		
		// 브라우저의 크기가 변했을때 이미지 선택 박스의 위치를 재조정
		$(window).resize(function() {
			if ($("#image-select-cnt").css("display") === "flex") {
				positionSelectImage();
			}
		});
		
		$("#image-cancel-btn").click(function() {
			$("#image-select-cnt").hide("fast");
			$("#image-url-input").val("");
		});
		
		// 이미지 선택박스 외에 다른 부분 클릭 시 박스를 안보이게 한다
		$(document).click(function(event) {
			let $imageBtn = $("#container > div.ql-toolbar.ql-snow > span:nth-child(5) > button.ql-image");
			let $imageSelect = $("#image-select-cnt");
			if (!$imageSelect.is(event.target) && $imageSelect.has(event.target).length === 0 && !$imageBtn.is(event.target) && $imageBtn.has(event.target).length === 0) {
				$imageSelect.hide("fast");
				$("#image-url-input").val("");
			}
		});
		
		$("#file-select-btn").click(function() {
			selectImageFile();
			$("#image-select-cnt").hide("fast");
			$("#image-url-input").val("");
		});
		
		$("#url-submit-btn").click(function() {
			let imageUrl = $("#image-url-input").val();
			const range = quill.getSelection();
			quill.insertEmbed(range === null ? 0 : range.index, 'image', imageUrl);
			$("#image-select-cnt").hide("fast");
			$("#image-url-input").val("");
		});
	});	
	
      function formCheck(event) {
    	// ajax요청이 비동기라 async: false를 하지않는 한 요청이 완료되기 전에 폼이 제출되어버리므로 폼제출을 막음
    	  event.preventDefault();
    	  
    	  if(document.querySelector("#title").value.length < 5) {
    		  alert("제목을 5글자 이상 작성해 주세요!");
    		  document.querySelector("#title").select();
    		  return false;
    	  }
    	  if(document.querySelector(".ql-editor").innerText == "\n") {
    		  alert("내용을 작성해 주세요!");
    		  quill.focus();
    		  return false;
    	  }
    	  
    	  $("#loading-cnt").css("display", "block");
    	  
    	// 텍스트 에디터 안에 img태그 요소를 가져옴
    	  const imgTags = document.querySelectorAll(".ql-editor img");
    	  // ajax요청을 저장할 배열 만들기
    	  const ajaxRequests = [];
    	  // 각 img 요소로 반복문 실행
    	  imgTags.forEach(function(img) {
    		  // 각 img 요소의 src 속성값을 가져옴
    		  let currentSrc = img.getAttribute("src");
    		  
    		  // base64로 인코딩된 이미지인지 확인
    		  if(currentSrc.startsWith("data:image")) {
    			  // base64로 인코딩 된 경우
    			  // MIME타입에 관한 부분과 base64 데이터 부분으로 나눔
    			  const splitSrc = currentSrc.split(",");
    			  // data 전송을 위해 FormData객체에 저장
    			  const formData = new FormData();
    			  formData.append("base64Src", splitSrc);
    			  
    			  // base64로 인코딩된건지 한번더 정확히 확인 후 ajax 업로드 요청
    			  if(splitSrc[0].includes("base64")) {
    				  const ajaxRequest = $.ajax({
    					  type: 'post',
    					  url: '/ch2/file/uploadBase64',
    					  data: formData,
    					  processData: false,
    					  contentType: false,
    					  dataType: 'text',
    					  success: function(fileName) {
    						  // 성공시 반환된 파일이름으로 이미지 src값 설정해서 이미지 보여주기
    						  img.setAttribute("src", "/ch2/file/display?fileName=" + fileName);
    					  },
    					  error: function() {
    						  console.log("base64 이미지 업로드 실패!");
    						  console.dir(img);
    					  }
    				  });
    				  // 완료,성공,실패 여부를 확인하기위해 각 요청 객체를 배열에 추가
    				  ajaxRequests.push(ajaxRequest);
    			  }
    		  }
    	  });
    	  // 모든 ajax 요청이 완료된 후에 폼을 제출함, 하나의 요청객체라도 실패하면 fail함수 실행으로 폼제출하지 않음
    	  $.when.apply($, ajaxRequests).done(function() {
    		  // 에디터에 있는 html텍스트를 hidden input에 담음
    		  document.querySelector("#contents").value = ql.innerHTML;
    		  document.querySelector("#container").submit();
    	  }).fail(function() {
    		  $("#loading-cnt").css("display", "none");
    		  alert("base64 이미지 업로드가 실패했습니다!");
    	  });
      }
      
      function positionSelectImage() {
    	  let $imageBtn = $("#container > div.ql-toolbar.ql-snow > span:nth-child(5) > button.ql-image");
    	  
    	  let offset = $imageBtn.offset();
    	  $("#image-select-cnt").css({
    		  top: offset.top + $imageBtn.outerHeight(),
    	  	  left: offset.left
    	  });
      }
      
      // quill 에디터 툴바 이미지 버튼 클릭 시 실행될 핸들러 함수
      function selectImage() {
    	  // 이미지 선택 박스가 안보이면 보여주고, 보이면 안보이게 한다
    	  if ($("#image-select-cnt").css("display") === "none") {
	    	  $("#image-select-cnt").show("fast");
    	  } else {
    		  $("#image-select-cnt").hide("fast");
    	  }
      }
      
      // quill에디터 툴바 이미지버튼 클릭후 파일선택버튼 클릭 시 실행될 함수
      function selectImageFile() {
    	  // input type file 엘레먼트를 만듬
    	  const fileInput = document.createElement("input");
    	  fileInput.setAttribute("type", "file");
    	  // 업로드 파일 선택 시 선택창에서 해당 MIME타입만 나타나도록 한다
    	  fileInput.accept = "image/*";
    	  // 이미지버튼 클릭시 input이 클릭되어 파일선택창 나타남
    	  fileInput.click();
    	  
    	  // 파일이 선택되면 실행할 함수
    	  fileInput.addEventListener("change", function() {
    		  // input type file의 val()은 파일이름을 문자열로 반환함
    		  // 파일을 선택하지 않거나 취소하는 경우 빈문자열을 반환한다
    		  if($(this).val() !== "") {
    			  // 파일이름에서 확장자 분리해서 정해진 확장자만 업로드 가능하도록 검사
    			  let ext = $(this).val().split(".").pop().toLowerCase();
    			  if($.inArray(ext, ["gif", "jpg", "jpeg", "png", "bmp"]) == -1) {
    				  alert("gif, jpg, jpeg, png, bmp파일만 선택가능합니다!");
    				  return;
    			  }
    			  
    			  // input type file의 files속성을 통해 선택된 파일에 접근(파일이름말고 실제 파일에 접근하는것)
    			  let fileSize = this.files[0].size;
    			  let maxSize = 20 * 1024 * 1024;
    			  if(fileSize > maxSize) {
    				  alert("업로드 가능한 파일의 최대용량은 20MB입니다!");
    				  return;
    			  }
    			  
    			  const formData = new FormData();
    			  formData.append("uploadFile", this.files[0]);
    			  
    			  $.ajax({
    				  type: 'post',
    				  enctype: 'multipart/form-data',
    				  url: '/ch2/file/upload',
    				  data: formData,
    				  processData: false,
    				  contentType: false,
    				  dataType: 'text',
    				  success: function(fileName) {
    					  const range = quill.getSelection();
    					  quill.insertEmbed(range === null ? 0 : range.index, 'image', '/ch2/file/display?fileName=' + fileName);
    				  },
    				  error: function() {
    					  alert("파일 업로드에 실패했습니다!");
    				  }
    			  });
    		  }
    	  });
      }
      
		const toolbarOptions = [
			[{ 'size': ['small', false, 'large', 'huge'] }],
			 [{ 'header': 1 }, { 'header': 2 }],
			 ['bold', 'italic', 'underline', 'strike'],
			 ['blockquote', 'code-block'],
			 ['link', 'image', 'video'],
			 [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
			 ['clean']
		];
	
      const quill = new Quill("#editor", {
    	  modules: {
    		  toolbar: {
				  container: toolbarOptions,
				  handlers: {
					  image: selectImage
				  }
			  }
    	  },
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
	
    ql.innerHTML = document.querySelector("#contents").value;
      
	let msg = "${msg}";
	if(msg=="UPD_ERR") alert("게시글을 수정하는데 실패했습니다!");
    </script>
</body>
</html>

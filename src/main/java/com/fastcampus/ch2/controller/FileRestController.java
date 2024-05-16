package com.fastcampus.ch2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileRestController {
	@PostMapping("/upload")
	// 업로드된 파일은 MultipartFile타입으로 자동 변환 바인딩됨(formData.append의 키랑 변수명이랑 일치해야함, form의
	// name이랑 일치시키는거랑 같음)
	public ResponseEntity<String> uploadFile(MultipartFile uploadFile) {
		try {
			// 저장경로를 변수에 저장(윈도우는 원래 파일경로가 '\'인데, '/'써도 파일경로로 인식됨)
			String savePath = System.getProperty("user.home") + "/Desktop/uploadTest/image";

			// 메서드 사용을 위해 savePath경로로 File객체 생성
			File uploadPath = new File(savePath);

			// savePath에 저장한 파일경로가 존재하지않으면
			if (!uploadPath.exists()) {
				// 상위 디렉토리까지 없으면 다 만들어줌
				uploadPath.mkdirs();
			}

			// 업로드된 파일의 이름을 저장
			String uploadFileName = uploadFile.getOriginalFilename();
			// 파일 이름의 중복을 방지하기 위해 랜덤한 고유식별자 생성 후 파일이름 앞에 붙여줌
			String uuid = UUID.randomUUID().toString();
			uploadFileName = uuid + "_" + uploadFileName;

			// 파일 저장할 경로에 파일이름 붙여서 File객체 생성 (실제파일을 저장한게 아니고 경로와 이름만 저장한거임)
			File saveFile = new File(uploadPath, uploadFileName);

			// saveFile에 저장된 경로와 파일이름으로 업로드된 파일을 실제로 저장
			uploadFile.transferTo(saveFile);
			// 성공시 파일이름을 url인코딩 한 후 리턴(파일이름에 한글 포함시 인코딩 안해주면 엑박뜸)
			uploadFileName = URLEncoder.encode(uploadFileName);
			return new ResponseEntity<String>(uploadFileName, HttpStatus.OK);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/uploadBase64")
	public ResponseEntity<String> uploadBase64(String[] base64Src) {
		try {
			// 전달받은 데이터는 base64 src를 ','기준으로 split한 문자열 배열
			String base64Header = base64Src[0];
			String base64Data = base64Src[1];
			String extension = "";

			// 정규표현식을 이용해서 base64헤더부분에서 확장자를 찾아냄
			Pattern pattern = Pattern.compile("^data:image/([A-Za-z]+);base64");
			Matcher matcher = pattern.matcher(base64Header);
			if (matcher.find()) {
				extension = matcher.group(1).toLowerCase();
			}

			// UUID 생성
			String uuid = UUID.randomUUID().toString();
			// timestamp 생성
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			// 파일이름 저장
			String fileName = uuid + "_" + timestamp + "." + extension;

			// 저장경로를 변수에 저장
			String savePath = System.getProperty("user.home") + "/Desktop/uploadTest/image";
			// 메서드 사용을 위해 savePath경로로 File객체 생성
			File uploadPath = new File(savePath);
			// savePath에 저장한 파일경로가 존재하지않으면
			if (!uploadPath.exists()) {
				// 상위 디렉토리까지 없으면 다 만들어줌
				uploadPath.mkdirs();
			}
			
			// 저장경로에 파일이름 붙여서 파일경로 저장 후 파일객체 생성
			String filePath = savePath + "/" + fileName;
			File file = new File(filePath);
			
			// base64인코딩 된 data를 문자열에서 바이트배열로 변환, 디코딩 후 파일 저장
			Base64.Decoder decoder = Base64.getMimeDecoder();
			byte[] decodedBytes = decoder.decode(base64Data.getBytes());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(decodedBytes);
			fileOutputStream.close();
			
			return new ResponseEntity<String>(fileName, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> showImage(String fileName) {
		try {
			// fileName이 URL인코딩 되어있기때문에 파일이름에 한글포함시 파일을 제대로 찾기위해 디코딩 해줘야함
			fileName = URLDecoder.decode(fileName, "UTF-8");
			// 저장경로를 변수에 저장
			String savePath = System.getProperty("user.home") + "/Desktop/uploadTest/image/";

			// 저장경로와 파일이름으로 File객체 생성(아직 파일 불러 온거 아니고 불러올 파일 경로와 이름만 저장한거)
			File file = new File(savePath + fileName);

			// 헤더 객체 생성
			HttpHeaders header = new HttpHeaders();
			// 불러올 파일의 MIME타입 검사해서 Content-type으로 저장
			header.add("Content-type", Files.probeContentType(file.toPath()));

			// 파일경로와 이름으로 파일 찾아서 바이트 배열로 변환, 복사해서 헤더와 상태코드와 함께 리턴
			return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
	}
}

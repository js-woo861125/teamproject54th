package ks54team01.common.file.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.common.file.domain.FileMetaData;



@Component
public class FilesUtils {
	
	@Value("${file.path}")
	private String fileRealPath;
	// 디렉토리 이름 포맷 설정
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	// 파일 인덱스 이름 포맷 설정
	private static final DateTimeFormatter FILEIDX_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
	
	/**
	 * 경로를 입력받아서 서버에 디렉토리 생성
	 * @param path
	 */
	private void createDirectory(Path path) {
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("디렉토리 생성 실패" + path, e);
		}
	}
	
	/**
	 * 파일 저장 로직 (디렉토리생성, 파일 중복 회피 새로운 파일명 생성, 완성된 파일 경로 저장)
	 * @param multipartFile
	 * @return FileMetaData (저장된 파일객체의 메타데이터 저장)
	 */
	private FileMetaData storeFile(MultipartFile multipartFile) {
		if(multipartFile.isEmpty()) return null;
		
		// /home/teameproject/attachment/20250513/image/~~~~~~.png
		// /home/teameproject/attachment/20250513/files/~~~~~~.xlsx
		// 날짜 디렉토리 명 설정
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
		String dateDirectory = now.format(DATE_FORMATTER); //20250513 형태로 출력
		
		// 파일분류
		String contentType = multipartFile.getContentType();
		// 파일분류 별 디렉토리명 설정
		String typeDirectory = (contentType != null && contentType.contains("image")) ? "image" : "files";
		 
		// os별 루트 설정
		String osName = System.getProperty("os.name").toLowerCase();
		String osRoot = osName.contains("win") ? "d:" : "";
		String rootFilePath = osRoot + fileRealPath;
		
		// 디렉토리 폴더 경로 설정
		Path directoryPath = Paths.get(rootFilePath, "attachment", dateDirectory, typeDirectory);
		
		// 디렉토리 생성
		createDirectory(directoryPath); //home/teameproject/attachment/20250513/files/ 경로 폴더 생성
		
		String originalFilename = multipartFile.getOriginalFilename();
		String extension = null;
		if(originalFilename == null) extension = "";
		
		int dotIdx = originalFilename.lastIndexOf(".");
		extension = (dotIdx != -1 && dotIdx < originalFilename.length() -1) ? "." + originalFilename.substring(dotIdx + 1) : "";
		
		
		
		// 새로운 파일명 생성 (동일 파일명이 존재시 덮어쓰여지기 때문에 새로운 이름으로 설정)
		String newFileName = UUID.randomUUID() + extension;
		
		// 파일의 최종 경로 생성
		Path uploadPath = directoryPath.resolve(newFileName);
		
		// 파일의 메타데이터 객체 저장
		FileMetaData fileMetaData = null;
		
		try {
			Files.write(uploadPath, multipartFile.getBytes());
			
			// 파일 인덱스 생성
			// file_250513550e8400e29b1a4a3s3
			String fileIdx = "file_" + now.format(FILEIDX_FORMATTER)
									 + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
			
			
			fileMetaData = FileMetaData.builder().fileIdx(fileIdx)
												 .fileNewName(newFileName)
												 .fileOriginalName(originalFilename)
												 .filePath(uploadPath.toString().replace("c:","/").replace("\\","/").replace(fileRealPath, ""))
												 .fileSize(multipartFile.getSize())
												 .build();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileMetaData;
	}	
	
	/**
	 * 파일 저장 로직 (디렉토리생성, 파일 중복 회피 새로운 파일명 생성, 완성된 파일 경로 저장)
	 * @param multipartFile
	 * @return FileMetaData (저장된 파일객체의 메타데이터 저장)
	 */
	private FileMetaData storeFile(MultipartFile multipartFile, String imgType) {
		if(multipartFile.isEmpty()) return null;
		
		// /home/teameproject/attachment/20250513/image/~~~~~~.png
		// /home/teameproject/attachment/20250513/files/~~~~~~.xlsx
		// 날짜 디렉토리 명 설정
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
		String dateDirectory = now.format(DATE_FORMATTER); //20250513 형태로 출력
		
		// 파일분류
		String contentType = multipartFile.getContentType();
		// 파일분류 별 디렉토리명 설정
		String typeDirectory = (contentType != null && contentType.contains("image")) ? "image" : "files";
		String imgTypeDirectory = null;
		if(typeDirectory.equals("image")) {
			imgTypeDirectory = (imgType != null && imgType.contains("thumnail")) ? "thumnail" : "product";
		}
		 
		// os별 루트 설정
		String osName = System.getProperty("os.name").toLowerCase();
		String osRoot = osName.contains("win") ? "d:" : "";
		String rootFilePath = osRoot + fileRealPath;
		
		// 디렉토리 폴더 경로 설정
		Path directoryPath = Paths.get(rootFilePath, "attachment", dateDirectory, typeDirectory, imgTypeDirectory);
		
		// 디렉토리 생성
		createDirectory(directoryPath); //home/teameproject/attachment/20250513/files/ 경로 폴더 생성
		
		String originalFilename = multipartFile.getOriginalFilename();
		String extension = null;
		if(originalFilename == null) extension = "";
		
		int dotIdx = originalFilename.lastIndexOf(".");
		extension = (dotIdx != -1 && dotIdx < originalFilename.length() -1) ? "." + originalFilename.substring(dotIdx + 1) : "";
		
		
		
		// 새로운 파일명 생성 (동일 파일명이 존재시 덮어쓰여지기 때문에 새로운 이름으로 설정)
		String newFileName = UUID.randomUUID() + extension;
		
		// 파일의 최종 경로 생성
		Path uploadPath = directoryPath.resolve(newFileName);
		
		// 파일의 메타데이터 객체 저장
		FileMetaData fileMetaData = null;
		
		try {
			Files.write(uploadPath, multipartFile.getBytes());
			
			// 파일 인덱스 생성
			// file_250513550e8400e29b1a4a3s3
			String fileIdx = "file_" + now.format(FILEIDX_FORMATTER)
									 + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
			
			
			fileMetaData = FileMetaData.builder().fileIdx(fileIdx)
												 .fileNewName(newFileName)
												 .fileOriginalName(originalFilename)
												 .filePath(uploadPath.toString().replace("c:","/").replace("\\","/").replace(fileRealPath, ""))
												 .fileSize(multipartFile.getSize())
												 .build();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileMetaData;
	}	
	
	
	// 단일 파일 저장
	public FileMetaData uploadFile(MultipartFile multipartFile) {
		
		FileMetaData fileMetaData = storeFile(multipartFile);
		
		return fileMetaData;
	}
	
	
	// 다중 파일 저장
		public List<FileMetaData> uploadFiles (MultipartFile[] multipartFiles){
			
			List<FileMetaData> fileList = new ArrayList<FileMetaData>();
			FileMetaData fileMetaData = null;
			for(MultipartFile multipartFile : multipartFiles) {
				fileMetaData = storeFile(multipartFile);
				if(fileMetaData != null) fileList.add(fileMetaData);
			}
			return fileList;
		}
		
	// 다중 파일 저장
	public List<FileMetaData> uploadFiles (MultipartFile[] multipartFiles, String imgType){
		
		List<FileMetaData> fileList = new ArrayList<FileMetaData>();
		FileMetaData fileMetaData = null;
		for(MultipartFile multipartFile : multipartFiles) {
			fileMetaData = storeFile(multipartFile, imgType);
			if(fileMetaData != null) fileList.add(fileMetaData);
		}
		return fileList;
	}
	
	
	
}

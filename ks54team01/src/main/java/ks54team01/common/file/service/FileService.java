package ks54team01.common.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ks54team01.common.file.domain.FileMetaData;

public interface FileService {
	
		// 파일 목록 조회
		List<FileMetaData> getFileList();
		
		// 단일 파일 업로드
		void addFile(MultipartFile multipartFile);
		
		// 다중 파일 업로드
		void addFiles(MultipartFile[] multipartFiles);
				
		// 다중 파일 업로드
		void addFiles(MultipartFile[] multipartFiles, String imgType);
		
		int addFiles(MultipartFile[] multipartFiles, String fileType, String refId);
		
		List<FileMetaData> uploadFiles(MultipartFile[] multipartFiles, String imgType);
}

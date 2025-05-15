package ks54team01.common.file.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.mapper.FileMapper;
import ks54team01.common.file.service.FileService;
import ks54team01.common.file.util.FilesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
	private final FilesUtils filesUtils;
	private final FileMapper fileMapper;
	
	@Override
	public List<FileMetaData> getFileList() {
		List<FileMetaData> fileList = fileMapper.getFileList();
		return fileList;
	}
	
	
	@Override
	public void addFile(MultipartFile multipartFile) {
		
		FileMetaData fileInfo = filesUtils.uploadFile(multipartFile);
		log.info("업로드 파일메타데이터 : {}", fileInfo);
		if(fileInfo != null) fileMapper.addFile(fileInfo);
	}
	
	@Override
	public void addFiles(MultipartFile[] multipartFiles) {
		
		List<FileMetaData> fileList = filesUtils.uploadFiles(multipartFiles);
		log.info("업로드 파일메타데이터 : {}", fileList);
		/* if(!fileList.isEmpty()) fileMapper.addFiles(fileList); */
	}
	
	@Override
	public void addFiles(MultipartFile[] multipartFiles, String imgType) {
		
		List<FileMetaData> fileList = filesUtils.uploadFiles(multipartFiles, imgType);
		log.info("업로드 파일메타데이터 : {}", fileList);
		/* if(!fileList.isEmpty()) fileMapper.addFiles(fileList); */
	}
	
}

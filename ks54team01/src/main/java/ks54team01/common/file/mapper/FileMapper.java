package ks54team01.common.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks54team01.common.file.domain.FileMetaData;

@Mapper
public interface FileMapper {
		// 단일 파일 메타데이터 테이블 저장
		int addFile(FileMetaData fileMetaData);
		
		// 다중 파일 메타데이터 테이블 저장
		int addFiles(List<FileMetaData> fileMetaData);

		// 파일 목록 조회
		List<FileMetaData> getFileList();
		
		// 특정 파일 메타데이터 조회
		FileMetaData getFileInfoByIdx(String fileIdx);
		
		List<FileMetaData> getFileListByRefId(@Param("refId") String refId, @Param("fileType") String fileType);
		
		 void deleteFilesByRefIdAndType(@Param("refId") String refId, @Param("fileType") String fileType);
}

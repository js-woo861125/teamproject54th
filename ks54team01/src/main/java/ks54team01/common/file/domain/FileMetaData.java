package ks54team01.common.file.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileMetaData {
		private String fileIdx;
	    private String refId;
	    private String fileOriginalName;
	    private String fileNewName;
	    private String filePath;
	    private Long fileSize;
	    private String fileType;
	    private LocalDateTime registerDate;
	    private LocalDateTime revisionDate;
}

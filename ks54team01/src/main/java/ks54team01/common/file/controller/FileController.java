package ks54team01.common.file.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.mapper.FileMapper;
import ks54team01.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/file")
public class FileController {
	@Value("${file.path}")
	private String fileRealPath;
	
	
	private final FileMapper fileMapper;
	private final FileService fileService;
	
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<Object> downloadFile(@RequestParam(value="fileIdx", required = false) String fileIdx, HttpServletRequest request, HttpServletResponse response) throws IOException{
		// fileIdx 없다면 관리자 메인페이지 리디렉션
		if(fileIdx == null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}
		FileMetaData fileMetaData = fileMapper.getFileInfoByIdx(fileIdx);
		
		String osName = System.getProperty("os.name").toLowerCase();
		String rootFilePath = osName.contains("win") ? "d:" + fileRealPath : fileRealPath;
		
		Path filePath = Paths.get(rootFilePath, fileMetaData.getFilePath());
		Resource resource;
		
		
		try {
			resource = new UrlResource(filePath.toUri());
			String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			if(contentType == null) {
				contentType = "application/octet-stream";
			}
			ContentDisposition contentDisposition = ContentDisposition.attachment().filename(fileMetaData.getFileOriginalName(), StandardCharsets.UTF_8).build();
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString()).body(resource);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/admin"));
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
	
	@GetMapping("/fileDownload")
	public String fileDownload(Model model) {
		
		var fileList = fileService.getFileList();
		
		model.addAttribute("title", "파일 다운로드");
		model.addAttribute("fileList", fileList);
		
		return "admin/file/fileDownloadView";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(@RequestPart(name="files", required = false) MultipartFile[] files) {
		
		fileService.addFiles(files);
		
		return "redirect:/admin/file/upload";
	}
	
	@GetMapping("/upload")
	public String uploadView(Model model) {
		
		model.addAttribute("title", "파일업로드");
		
		return "admin/file/fileUpload";
	}
}

package ks54team01.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	
	  @ExceptionHandler(exception = Exception.class)
	  
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public String
	  globalExceptionHandler(Model model, Exception ex) {
	  model.addAttribute("telno", "1900 - 6666 or 1900 - 8888"); String
	  errorMessage = ex.getMessage(); String viewName = "error/500";
	  
	  if(errorMessage.contains("No static resource")) { viewName = "error/404"; }
	  log.error("{}", ex.getMessage());
	  
	  return viewName; }
	 

}

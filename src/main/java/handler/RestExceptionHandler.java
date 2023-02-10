package handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import error.ErrorMessage;
import exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorMessage error = new ErrorMessage ("Not found", HttpStatus.NOT_FOUND.value() , ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
		
	
}

package basicauth.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import basicauth.entity.ExceptionEntity;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	/* <======Basic Exceptions======> */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultException(Exception ex, WebRequest request) {
		ExceptionEntity exceptionEntity = new ExceptionEntity(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<Object>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex,
			WebRequest request) {
		ExceptionEntity exceptionEntity = new ExceptionEntity(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<Object>(exceptionEntity, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ExceptionEntity exceptionEntity = new ExceptionEntity(errors.toString(), request.getDescription(false),
				new Date());
		return new ResponseEntity<Object>(exceptionEntity, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionEntity exceptionEntity = new ExceptionEntity(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<Object>(exceptionEntity, status);
	}

}

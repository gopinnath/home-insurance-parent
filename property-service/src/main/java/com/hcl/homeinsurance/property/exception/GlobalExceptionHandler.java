/**
 * 
 */
package com.hcl.homeinsurance.property.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.homeinsurance.property.utility.ApplicationConstants;
import com.hcl.homeinsurance.property.utility.PropertyUtility;

/**
 * @author User1
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	  @ExceptionHandler(PropertyNotFoundException.class) public
	  ResponseEntity<ErrorResponse> error(PropertyNotFoundException ex) {
	  
	  ErrorResponse er = new ErrorResponse(); 
	  er.setMessage(ex.getMessage());
	  er.setStatus(ApplicationConstants.PROPERTY_FAILURE_CODE); 
	  return
	  new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	  
	  }
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) { List<Object> detail = new
	 * ArrayList<>(); for (ObjectError error : ex.getBindingResult().getAllErrors())
	 * { detail.add(error.getDefaultMessage());
	 * 
	 * } InputErrorResponse errorResponse = new InputErrorResponse(detail,
	 * "validation");
	 * 
	 * return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST); }
	 */
	  
	  @ExceptionHandler(SaveException.class)
		public ResponseEntity<ErrorResponse> customerErrorException(SaveException ex) {
			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatus(PropertyUtility.SAVE_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(UpdationException.class)
		public ResponseEntity<ErrorResponse> customerErrorException(UpdationException ex) {
			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatus(PropertyUtility.UPDATE_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(AddressException.class)
		public ResponseEntity<ErrorResponse> customerErrorException(AddressException ex) {
			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatus(PropertyUtility.ADDRESS_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		@ExceptionHandler(PropertyException.class)
		public ResponseEntity<ErrorResponse> customerErrorException(PropertyException ex) {
			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatus(PropertyUtility.PROPERTY_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	 
	 
}

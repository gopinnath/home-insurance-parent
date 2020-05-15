/**
 * 
 */
package com.hcl.dna.homeinsurance.quote.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.dna.homeinsurance.quote.utility.QuoteUtility;

/**
 * @author User1
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	
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
			errorResponse.setStatus(QuoteUtility.SAVE_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
	
		
		@ExceptionHandler(QuoteException.class)
		public ResponseEntity<ErrorResponse> customerErrorException(QuoteException ex) {
			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatus(QuoteUtility.QUOTE_ERROR_STATUS);

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

	
}

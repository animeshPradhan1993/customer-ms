package com.animesh.bank.customer.exception;

import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
public class ExceptionIntercepter {

	@AfterThrowing(value = "execution(* *(..)) &&  within(com.animesh.bank..controller..*))", throwing = "ex")
	public ResponseEntity<?> handleAllExceptions(Exception ex) {
		System.out.println("Inside Advice");
		if (ex instanceof BadRequestException) {
			System.out.println(ex.getMessage());
			return createBadRequestException(new BadRequestException(ex.getMessage()));
		}

		else if (ex instanceof CustomerNotFoundException) {
			return createResourceNotFoundException(new CustomerNotFoundException(ex.getMessage()));
		} else {
			return globleExcpetionHandler(ex);
		}
	}

	public ResponseEntity<?> globleExcpetionHandler(Exception ex) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage(), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> createResourceNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(),
				null);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> createBadRequestException(BadRequestException ex) {
		
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
}

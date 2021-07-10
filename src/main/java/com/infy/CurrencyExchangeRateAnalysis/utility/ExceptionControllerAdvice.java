package com.infy.CurrencyExchangeRateAnalysis.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.CurrencyExchangeRateAnalysis.exception.CurrencyExchangeException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@Autowired
	private Environment environment;

	@ExceptionHandler(CurrencyExchangeException.class)
	public ResponseEntity<ErrorInfo> CurrencyExchangeExceptionHandler(CurrencyExchangeException exception) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(ConstraintViolationException exception) {
		LOGGER.error(exception.getMessage(), exception);
		String errorMsg;
		ConstraintViolationException cvException = (ConstraintViolationException) exception;
		errorMsg = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(", "));
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(errorMsg);
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}

package com.goodchobo.common.reply;

import java.nio.file.AccessDeniedException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;

/**
 * Exception 통합 대응
 * @author user
 *
 */
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	/*
	 *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
	 *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
	 *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<ReplyVO<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("### handleMethodArgumentNotValidException= " + e.getMessage());

		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(ReplyStatusCode.INVALID_VALUE, e.getBindingResult()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
	 * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
	 */
	@ExceptionHandler(value = BindException.class)
	protected ResponseEntity<ReplyVO<Object>> handleBindException(BindException e) {
		log.error("### handleBindException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(ReplyStatusCode.INVALID_VALUE, e.getBindingResult()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * enum type 일치하지 않아 binding 못할 경우 발생
	 * 주로 @RequestParam enum으로 binding 못했을 경우 발생
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ReplyVO<Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		log.error("### handleMethodArgumentTypeMismatchException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(e));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ReplyVO<Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("### handleHttpRequestMethodNotSupportedException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(ReplyStatusCode.METHOD_NOT_ALLOWED));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
	protected ResponseEntity<ReplyVO<Object>> handleAccessDeniedException(AccessDeniedException e) {
		log.error("### handleAccessDeniedException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(ReplyStatusCode.HANDLE_ACCESS_DENIED));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler(value = BusinessException.class)
	protected ResponseEntity<ReplyVO<Object>> handleBusinessException(final BusinessException e) {
		log.error("### handleBusinessException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(e.getCode(), e.getAllErrors()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<ReplyVO<Object>> handleException(Exception e) {
		log.error("### handleException= " + e.getMessage());
		final ReplyVO<Object> response = new ReplyVO<Object>();
		response.setStatus(ReplyStatusVO.of(ReplyStatusCode.NOT_FOUND_DATA));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

package com.goodchobo.common.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

import com.goodchobo.common.enumeration.ReplyStatusCode;

/**
 * Custom Exception Parent
 * @author user
 *
 */
public class BusinessException extends Exception {
	protected List<ObjectError> allErrors;
	protected ReplyStatusCode code;
	/**
	 *
	 */
	private static final long serialVersionUID = -4132948746847421720L;

	public BusinessException() {
		this(ReplyStatusCode.UNKNOWN_ERROR);
	}

	public BusinessException(ReplyStatusCode code) {
		super();
		this.code = code;
	}

	public BusinessException setAllErrors(List<ObjectError> allErrors) {
		this.allErrors = allErrors;
		return this;
	}

	public ReplyStatusCode getCode() {
		return code;
	}

	public List<ObjectError> getAllErrors() {
		return allErrors;
	}

	@Override
	public String getMessage() {
		return code == null ? super.getMessage() : String.format("%s, %s", code.getCode(), code.getMessage());
	}

	@Override
	public String getLocalizedMessage() {
		return code == null ? super.getLocalizedMessage() : String.format("%s, %s", code.getCode(), code.getMessage());
	}
}

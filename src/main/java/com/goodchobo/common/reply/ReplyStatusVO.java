package com.goodchobo.common.reply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.goodchobo.common.enumeration.InvalidValueCode;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.InvalidValueException;

/**
 * 서버 응답 코드 VO
 * @author user
 *
 */
public class ReplyStatusVO {
	private String message;
	private List<ErrorField> errors;
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorField> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorField> errors) {
		this.errors = errors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static class ErrorField {
		private String field;
		private String reason;

		public ErrorField(String field, String reason) {
			this.field = field;
			this.reason = reason;
		}

		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
	}

	public static ReplyStatusVO of(ReplyStatusCode valueValidationError) {
		ReplyStatusVO responseVO = new ReplyStatusVO();
		responseVO.setCode(valueValidationError.getCode());
		responseVO.setMessage(valueValidationError.getMessage());

		return responseVO;
	}

	public static ReplyStatusVO of(ReplyStatusCode valueValidationError, List<ObjectError> allErrors) {
		ReplyStatusVO responseVO = of(valueValidationError);

		if(allErrors != null && allErrors.size() > 0) {
			List<ErrorField> errors = new ArrayList<ErrorField>();

			allErrors.forEach(e->{
				errors.add(new ErrorField(e.getCode(), InvalidValueCode.getReason(e.getCode())));
			});

			responseVO.setErrors(errors);
		}

		return responseVO;
	}

	public static ReplyStatusVO of(ReplyStatusCode valueValidationError, BindingResult bindingResult) {		
		if(bindingResult != null) {
			return of(valueValidationError, bindingResult.getAllErrors());
		} else {
			return of(valueValidationError);
		}
	}

	public static ReplyStatusVO of(Exception e) {		
		Class<? extends Exception> c = e.getClass();

		if(c == NullPointerException.class) {
			return of(ReplyStatusCode.MISSING_KEY_OR_VALUE);
		} else if(c == InvalidValueException.class) {
			return of(ReplyStatusCode.INVALID_VALUE);
		} else {
			return of(ReplyStatusCode.UNKNOWN_ERROR);
		}
	}
}

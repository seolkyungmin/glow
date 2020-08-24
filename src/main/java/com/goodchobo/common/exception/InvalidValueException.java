package com.goodchobo.common.exception;

import com.goodchobo.common.enumeration.ReplyStatusCode;

@SuppressWarnings("serial")
public class InvalidValueException extends BusinessException {
	
	public InvalidValueException() {
		super(ReplyStatusCode.INVALID_VALUE);
	}
}

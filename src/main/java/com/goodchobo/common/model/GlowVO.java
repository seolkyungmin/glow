package com.goodchobo.common.model;

import org.springframework.validation.Errors;

import com.goodchobo.common.enumeration.InvalidValueCode;
import com.goodchobo.common.util.StringUtil;

public class GlowVO extends BaseVO {
	private int id;		//PK


	@Override
	public boolean supports(Class<?> clazz) {
		return this.getClass().equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target == null) return;

		GlowVO noticeVO = (GlowVO) target;


	}

}

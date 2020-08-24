package com.goodchobo.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.goodchobo.common.enumeration.InvalidValueCode;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.exception.InvalidValueException;
import com.goodchobo.common.model.BaseVO;

/**
 * 모델 항목 유효 체크 유틸리티
 * @author user
 *
 */
public class ModelValidateUtil {
	/**
	 * BaseVO를 상속 받은 모델의 유효성 체크
	 * @param vo
	 * @throws BusinessException
	 */
	public static void checkModelValid(BaseVO vo) throws BusinessException {

		if(vo == null) throw new BusinessException(ReplyStatusCode.NOT_FOUND_DATA);

		Errors errors = new BeanPropertyBindingResult(vo, vo.getClass().getSimpleName());
		vo.validate(vo, errors);

		System.out.println("checkError: " + errors.hasErrors());

		if(errors.hasErrors()) {
			throw new InvalidValueException().setAllErrors(errors.getAllErrors());
		}
	}

	/**
	 * InvalidValueCode 기반 ObjectError 생성
	 * @param parameterName
	 * @param invalidValueCode
	 * @return ObjectError
	 */
	public static ObjectError createInvalidValueError(String parameterName, InvalidValueCode invalidValueCode) {
		return new ObjectError(parameterName, new String[] {invalidValueCode.getCode()}, new String[] {}, invalidValueCode.getCode());
	}

	/**
	 * Validator를 상속받은 Model VO가 없을 경우를 위하여 단건 Validator Error 생성
	 * @param parameterName
	 * @param code InvalidValueCode
	 * @return List<ObjectError>
	 */
	public static List<ObjectError> createInvalidValueErrorList(String parameterName, InvalidValueCode invalidValueCode) {

		List<ObjectError> validatorError = new ArrayList<ObjectError>();

		if(!StringUtil.isEmpty(parameterName))
			validatorError.add(createInvalidValueError(parameterName, invalidValueCode));

		return validatorError;
	}

	public static BusinessException createSingleInvalidValueError(String parameterName, InvalidValueCode invalidValueCode) throws BusinessException {
		return new InvalidValueException().setAllErrors(createInvalidValueErrorList(parameterName, invalidValueCode));
	}
}

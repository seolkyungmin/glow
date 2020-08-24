package com.goodchobo.common.enumeration;

/**
 * 값의 유효 체크 코드 및 메세지 정의
 * @author user
 *
 */
public enum InvalidValueCode {
	/**
	 * code value 규칙
	 *
	 * 대상이름.오류이유
	 *
	 * ex. name.empty -> 이름 항목 빈값
	 * ex. name.lengthOver -> 이름 항목 최대길이 오류
	 * ex. name.pattern -> 이름 항목 형식 오류 (영문으로 입력되거나 자음,모음 쪼개지는 등)
	 *
	 * 오류이유
	 *
	 * null -> null
	 * empty -> 빈값
	 *
	 * lengthOver -> 최대길이 오류
	 * lengthUnder -> 최소길이 미달
	 *
	 * pattern -> 형식 오류
	 *
	 * over -> 최대값 넘김
	 * under -> 최소값 미달
	 *
	 * error -> 특정할 수 없는 오류 상태
	 *
	 */

	DEFAULT("param.error","입력값 오류");

	private final String code;
	private final String message;
	InvalidValueCode(final String code, final String message) {
		this.message = message;
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

	public static InvalidValueCode fromString(String text) {
		for (InvalidValueCode b : InvalidValueCode.values()) {
			if (b.code.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}

	public static String getReason(String code) {
		InvalidValueCode enumItem = InvalidValueCode.fromString(code);

		if(enumItem == null) enumItem = DEFAULT;

		return enumItem.getMessage();
	}
}

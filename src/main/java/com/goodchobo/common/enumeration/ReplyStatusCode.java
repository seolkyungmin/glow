package com.goodchobo.common.enumeration;

/**
 * 서버 응답 코드+메세지 목록
 * @author user
 *
 */
public enum ReplyStatusCode {
	SUCCESS("0000", "정상처리되었습니다."),

	SERVER_MAINTENANCE("0001", "서버 점검 중입니다."),
	EXPIRED_SESSION("0002", "세션이 만료되었습니다."),
	EXPIRED_JWT_TOKEN("0003","토큰이 만료되었습니다."),
	INVALID_JWT_TOKEN("0004","토큰이 변조되었습니다."),

    HANDLE_ACCESS_DENIED("0003", "접근 권한이 없습니다."),
    METHOD_NOT_ALLOWED("0004", "사용 권한이 없습니다."),
    LIMIT_EXCEEDED("0005", "요청이 빈번합니다."),
    INACTIVE_ADMIN_STATUS("0006","권한이 비활성화 상태입니다."),
    INVALID_PERMISSION_MODIFY_SELF("0007", "자기 자신의 권한은 변경할 수 없습니다."),

    MISSING_KEY_OR_VALUE("0108", "속성을 변경할 key,value 값이 누락 됬습니다."),
    NOT_FOUND_DATA("0300", "데이터가 없습니다."),
    NOT_FOUND_LIST("0301", "리스트가 없습니다."),
    FAILED_SELECT_RDS("0311", "조회에 실패했습니다. 잠시 후 다시 시도해 주세요."),
    INVALID_VALUE("0400", "유효하지 않은 입력값입니다. 입력값을 확인해주세요."),

	NETWORK_ERROR("9996","네트워크 에러입니다."),
	PARAMETER_STORE_ERROR("9997", "Parameter Store 에러입니다."),
	COGNITO_ERROR("9998", "Cognito 에러입니다."),
	UNKNOWN_ERROR("9999", "시스템 에러입니다.");



    private final String code;
    private final String message;
	ReplyStatusCode(final String code, final String message) {
        this.message = message;
        this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}

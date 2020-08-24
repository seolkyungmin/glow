package com.goodchobo.common.enumeration;

public enum FunctionType {
	SELECT(1, "select"), // 목록 조회(조회 권한)
	GET(2,"get"), //상세페이지 조회 (조회 권한)
	INSERT(3, "include"), // 등록
	UPDATE(4, "update"), // 수정(수정,삭제 권한)
	DELETE(5, "delete"), // 삭제하기(수정,삭제 권한)
	APPLY(6, "apply"), // 반영하기
	ADMIN_PERMISSION(7, "admin_permission"); //회원 권한 관리

	private int level;
	private String codename;
	private FunctionType(int level, String codename) {
		this.level = level;
		this.codename = codename;
	}

	public int getLevel() {
		return level;
	}

	public String getCodename() {
		return codename;
	}
}

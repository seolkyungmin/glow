package com.goodchobo.common.reply;

import com.goodchobo.common.enumeration.ReplyStatusCode;

/**
 * 서버 응답 VO
 * -data: UI로 넘길 데이터 목록
 * -status: 서버 응답 코드 및 메세지, 에러 정의
 * @author user
 *
 * @param <T> : 전송할 data Template 지정 (기본 : Object)
 */
public class ReplyVO<T extends Object> {
	private T data;
	private ReplyStatusVO status;

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ReplyStatusVO getStatus() {
		return status;
	}
	public void setStatus(ReplyStatusVO status) {
		this.status = status;
	}

	/**
	 * 정상 응답에 사용할 ReplyVO 생성
	 * @param <T> : 넘길 data 템플릿 세팅
	 * @param data : 실 데이터(변수)
	 * @return ReplyVO
	 */
	public static <T> ReplyVO<T> createSuccessReply(T data) {
		ReplyVO<T> vo = new ReplyVO<T>();
		vo.setData(data);
		vo.setStatus(ReplyStatusVO.of(ReplyStatusCode.SUCCESS));

		return vo;
	}
}

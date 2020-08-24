package com.goodchobo.common.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goodchobo.common.enumeration.Consts;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.util.StringUtil;

import io.jsonwebtoken.Claims;


/**
 * TODO : servlet-context.xml에 설정 값 추가
 * @author pc
 *
 */

public class NonmemberInterceptor extends HandlerInterceptorAdapter  {

}

package com.goodchobo.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

public class MauritiusEncodingFilter extends CharacterEncodingFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		System.out.println("URL:" + request.getRequestURI() + " | Encoding: "+ request.getCharacterEncoding());
//		kgmobilians일때는 EUC-KR로 셋팅
		if ("/shop/purchase/kit/kgmobilians/payment".equals(request.getRequestURI())) {
			request.setCharacterEncoding("EUC-KR");
			response.setCharacterEncoding("EUC-KR");
			filterChain.doFilter(request, response);
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			super.doFilterInternal(request, response, filterChain);
		}
    }
}


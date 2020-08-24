package com.goodchobo.shop.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.util.StringUtil;
import com.goodchobo.shop.dao.GlowDao;
import com.goodchobo.shop.service.GlowService;

@Service
public class GlowServiceImpl implements GlowService{
	private static final Logger log = Logger.getLogger(GlowServiceImpl.class);

	@Inject
	private GlowDao GlowDao;

	public void test() {
		System.out.println("#########");
		System.out.println("#########");
		System.out.println("#########");
	}


}

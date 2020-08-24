package com.goodchobo.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goodchobo.common.enumeration.Consts;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.reply.ReplyVO;
import com.goodchobo.common.util.CommonUtil;
import com.goodchobo.common.util.StringUtil;
import com.goodchobo.shop.service.GlowService;

/**
 *
 * @author user
 *
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/shop")
public class GlowController {
	private static final Logger log = Logger.getLogger(GlowController.class);

	@Inject
	private GlowService GlowService;

}
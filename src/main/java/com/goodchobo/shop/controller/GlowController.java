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

import com.goodchobo.common.annotation.ApiFunctionType;
import com.goodchobo.common.enumeration.Consts;
import com.goodchobo.common.enumeration.FunctionType;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;
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
	private GlowService glowService;

	/**
	 * 사진 폴더 등록
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@ApiFunctionType(type = {FunctionType.INSERT})
	@RequestMapping(value="/glow/pictures/create", method=RequestMethod.POST)
	public Object insertPicture(@RequestBody PictureVO paramVO) throws Exception {
		log.debug("### GlowController.insertPicture : paramVO = " + StringUtil.nullCheckStr(paramVO));

		Map<String,Object> resultMap = new HashMap<String,Object>();

		glowService.insertPicture(paramVO);

		resultMap.put("id", paramVO.getId());

		return ReplyVO.createSuccessReply(resultMap);
	}

	/**
	 * 사진 폴더에 사진 등록
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@ApiFunctionType(type = {FunctionType.INSERT})
	@RequestMapping(value="/glow/pictures/child/create", method=RequestMethod.POST)
	public Object insertPictureChild(@RequestBody PictureVO paramVO) throws Exception {
		log.debug("### GlowController.insertPictureChild : paramVO = " + StringUtil.nullCheckStr(paramVO));

		Map<String,Object> resultMap = new HashMap<String,Object>();

		glowService.insertPictureChild(paramVO);

		resultMap.put("id", paramVO.getId());

		return ReplyVO.createSuccessReply(resultMap);
	}
	/**
	 *  유저는 자신의 폴더를 생성 순서대로 조회할 수 있으며, 이 때에 각 폴더에 저장된 이미지 갯수를 알 수 있다.
		유저는 특정 폴더에서 최근 저장한 순서대로 사진을 조회할 수 있다.
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@ApiFunctionType(type = {FunctionType.GET})
	@RequestMapping(value= "/glow/pictures", method= {RequestMethod.GET})
	public Object selectPicture(GlowVO paramVO) throws Exception {
		log.debug("### GlowController.selectPicture : paramVO = " + StringUtil.nullCheckStr(paramVO));

		Map<String,Object> resultMap = new HashMap<String,Object>();

		resultMap.put("list", glowService.selectPicture(paramVO));

		return ReplyVO.createSuccessReply(resultMap);
	}
}
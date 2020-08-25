package com.goodchobo.shop.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;
import com.goodchobo.common.util.StringUtil;
import com.goodchobo.shop.dao.GlowDao;
import com.goodchobo.shop.service.GlowService;

@Service
public class GlowServiceImpl implements GlowService{
	private static final Logger log = Logger.getLogger(GlowServiceImpl.class);

	@Inject
	private GlowDao glowDao;

	@Override
	@Transactional(rollbackFor = {Exception.class, BusinessException.class})
	public int insertPicture(PictureVO paramVO) throws BusinessException {

		log.debug("GlowServiceImpl.insertPicture : PictureVO = " + StringUtil.nullCheckStr(paramVO));

		// 폴더이름 중복체크
		int dupCount = glowDao.checkDuplicationPicture(paramVO);

		if(dupCount > 0) {
			throw new BusinessException(ReplyStatusCode.DUPLICATE_CODENAME);
		}

		int result = glowDao.insertPicture(paramVO);

		if(result < 0) {
			throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
		}

		return result;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class, BusinessException.class})
	public int insertPictureChild(PictureVO paramVO) throws BusinessException {
		log.debug("GlowServiceImpl.insertPictureChild : PictureVO = " + StringUtil.nullCheckStr(paramVO));

		int result = 0;

		for(PictureChildVO pChild : paramVO.getPictureChildList()) {
			result = glowDao.insertPictureChild(pChild);
			if(result < 0) {
				throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
			}
		}
		paramVO.setId(paramVO.getId());

		return result;
	}
	/**
	 * 유저는 자신의 폴더를 생성 순서대로 조회할 수 있으며, 이 때에 각 폴더에 저장된 이미지 갯수를 알 수 있다.
	   유저는 특정 폴더에서 최근 저장한 순서대로 사진을 조회할 수 있다.
	 */
	@Override
	public ArrayList<PictureVO> selectPicture(GlowVO paramVO) throws BusinessException {
		log.debug("### GlowServiceImpl.selectPicture : PictureVO = " + StringUtil.nullCheckStr(paramVO));
		try {
			ArrayList<PictureVO> pictureList = new ArrayList<PictureVO>();
			pictureList = glowDao.selectPicture(paramVO);
			int index=0;
			for(PictureVO p : pictureList) {
				ArrayList<PictureChildVO> pictureChildList = new ArrayList<PictureChildVO>();
				pictureChildList = glowDao.selectPictureChild(p);
				pictureList.get(index).setPictureChildList(pictureChildList);
				index++;
			}

			return pictureList;
		} catch (Exception e) {
			log.error("GlowServiceImpl.selectPicture :: " + e.getMessage());
			throw new BusinessException(ReplyStatusCode.FAILED_SELECT_RDS);
		}
	}


}

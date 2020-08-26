package com.goodchobo.shop.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodchobo.common.enumeration.Consts;
import com.goodchobo.common.enumeration.ReplyStatusCode;
import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;
import com.goodchobo.common.model.PointLogVO;
import com.goodchobo.common.model.TagVO;
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
		// 다른사람과 폴더이름 중복 안되게 함
		paramVO.setName(paramVO.getUserId()+"_"+paramVO.getName());
		int result = glowDao.insertPicture(paramVO);

		if(result < 0) {
			throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
		}

		//폴더 생성시 1000point 플러스
		GlowVO glow = new GlowVO();
		glow.setId(paramVO.getUserId());
		glow = glowDao.selectGlow(glow);
		int glowPoint = glow.getPoints();
		glowPoint += Consts.PICTURE_CREATE_POINT;
		glow.setPoints(glowPoint);
		int pointResult = glowDao.insertGlowPoint(glow);

		if(pointResult < 0) {
			throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
		}

		//4. 포인트에 대한 선입/선출
		int pointLog = 0;
		pointLog = insertLog(paramVO, Consts.PICTURE_TYPE);

		if(pointLog < 0) {
			throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
		}

		return result;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class, BusinessException.class})
	public int insertPictureChild(PictureVO paramVO) throws BusinessException {
		log.debug("GlowServiceImpl.insertPictureChild : PictureVO = " + StringUtil.nullCheckStr(paramVO));

		int result = 0;
		int index = 0;
		int tagResult = 0;

		int pictureChildSize = paramVO.getPictureChildList().size();
		int minusPoint = Consts.PICTURE_CHILD_CREATE_POINT;
		//사진 갯수로 차감 포인트 확인
		minusPoint = pictureChildSize * minusPoint;
		log.info("###################### minusPoint " + minusPoint);
		GlowVO glow = new GlowVO();
		glow.setId(paramVO.getUserId());
		glow = glowDao.selectGlow(glow);
		int glowPoint = glow.getPoints();
		//현재포인트
		log.info("###################### glowPoint " + glowPoint);
		//현재포인트와 사진 포인트 확인
		if(glowPoint >= minusPoint) {
			//현재에서 사진포인트 차감
			log.info("###################### glowPoint - minusPoint " + (glowPoint - minusPoint));
			glow.setPoints(glowPoint - minusPoint);
			int pointResult = glowDao.insertGlowPoint(glow);
			if(pointResult < 0) {
				throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
			}
		}else {
			throw new BusinessException(ReplyStatusCode.FAILED_PICTURE_INSERT_RDS);
		}

		for(PictureChildVO pChild : paramVO.getPictureChildList()) {
			result = glowDao.insertPictureChild(pChild);
			if(result < 0) {
				throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
			}

			int pictureChildId = paramVO.getPictureChildList().get(index).getId();
			ArrayList<TagVO> tagList = new ArrayList<TagVO>();
			tagList = (ArrayList<TagVO>) paramVO.getPictureChildList().get(index).getTagList();
			for(TagVO tag : tagList) {
				tag.setPictureChildId(pictureChildId);
				tagResult = glowDao.insertTag(tag);
				if(tagResult < 0) {
					throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
				}
			}
			index++;

			//4. 포인트에 대한 선입/선출
			int pointLog = 0;
			pointLog = insertLog(paramVO, Consts.PICTURE_CHILD_TYPE);

			if(pointLog < 0) {
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
	public ArrayList<PictureVO> selectPicture(PictureVO paramVO) throws BusinessException {
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

	@Override
	public ArrayList<TagVO> selectPictureTagRanking(TagVO paramVO) throws BusinessException {
		log.debug("### GlowServiceImpl.selectPictureTagRanking : PictureVO = " + StringUtil.nullCheckStr(paramVO));
		try {
			ArrayList<TagVO> tagRankingList = new ArrayList<TagVO>();
			tagRankingList = glowDao.selectPictureTagRanking(paramVO);

			return tagRankingList;
		} catch (Exception e) {
			log.error("GlowServiceImpl.selectPicture :: " + e.getMessage());
			throw new BusinessException(ReplyStatusCode.FAILED_SELECT_RDS);
		}
	}

	/**
	 * log 쌓는 공통 함수
	 * @param paramVO
	 * @param type
	 * @return
	 * @throws BusinessException
	 */
	public int insertLog(PictureVO paramVO, String type) throws BusinessException{
		PointLogVO pointLogVO = new PointLogVO();
		int pointLog = 0;
		if(type.equals("picture")) {
			pointLogVO.setPoints(Consts.PICTURE_CREATE_POINT);
			pointLogVO.setLogData(Consts.PICTURE_CREATE_POINT_LOG);
		}else if(type.equals("pictureChild")) {
			pointLogVO.setPoints(Consts.PICTURE_CHILD_CREATE_POINT);
			pointLogVO.setLogData(Consts.PICTURE_CHILD_CREATE_POINT_LOG);
		}

		pointLogVO.setUserId(paramVO.getUserId());
		pointLogVO.setPictureId(paramVO.getId());
		pointLog = glowDao.insertPointLog(pointLogVO);

		if(pointLog < 0) {
			throw new BusinessException(ReplyStatusCode.FAILED_INSERT_RDS);
		}

		return pointLog;
	}

	@Override
	public ArrayList<PictureVO> selectPicturePointStats(PictureVO paramVO) throws BusinessException {
		log.debug("### GlowServiceImpl.selectPicturePointStats : PictureVO = " + StringUtil.nullCheckStr(paramVO));
		try {

			ArrayList<PictureVO> pictureList = new ArrayList<PictureVO>();
			pictureList = glowDao.selectPicturePointStats(paramVO);
			ArrayList<PointLogVO> pointLogList = new ArrayList<PointLogVO>();
			PointLogVO pointLog = new PointLogVO();
			for(PictureVO picture : pictureList) {
				log.info("######################## " + picture.getId());
				pointLog.setUserId(paramVO.getUserId());
				pointLog.setPictureId(picture.getId());
				pointLogList = glowDao.selectPicturePointDetailStats(pointLog);
				picture.setPointLogList(pointLogList);
			}

			return pictureList;
		} catch (Exception e) {
			log.error("GlowServiceImpl.selectPicturePointStats :: " + e.getMessage());
			throw new BusinessException(ReplyStatusCode.FAILED_SELECT_RDS);
		}
	}

	@Override
	public ArrayList<PointLogVO> selectPictureUnused(PointLogVO paramVO) throws BusinessException {
		log.debug("### GlowServiceImpl.selectPictureUnused : PictureVO = " + StringUtil.nullCheckStr(paramVO));
		try {
			return glowDao.selectPictureUnused(paramVO);
		} catch (Exception e) {
			log.error("GlowServiceImpl.selectPictureUnused :: " + e.getMessage());
			throw new BusinessException(ReplyStatusCode.FAILED_SELECT_RDS);
		}
	}

}

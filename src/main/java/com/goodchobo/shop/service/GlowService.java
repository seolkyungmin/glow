package com.goodchobo.shop.service;

import java.util.ArrayList;

import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;
import com.goodchobo.common.model.PointLogVO;
import com.goodchobo.common.model.TagVO;

public interface GlowService {

	public int insertPicture(PictureVO paramVO) throws BusinessException;
	public int insertPictureChild(PictureVO paramVO) throws BusinessException;

	public ArrayList<PictureVO> selectPicture(PictureVO paramVO) throws BusinessException;
	public ArrayList<TagVO> selectPictureTagRanking(TagVO paramVO) throws BusinessException;
	public ArrayList<PictureVO> selectPicturePointStats(PictureVO paramVO) throws BusinessException;
	public ArrayList<PointLogVO> selectPictureUnused(PointLogVO paramVO) throws BusinessException;

}

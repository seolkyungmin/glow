package com.goodchobo.shop.service;

import java.util.ArrayList;

import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;

public interface GlowService {

	public int insertPicture(PictureVO paramVO) throws BusinessException;
	public int insertPictureChild(PictureVO paramVO) throws BusinessException;

	public ArrayList<PictureVO> selectPicture(GlowVO paramVO) throws BusinessException;

}

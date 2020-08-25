package com.goodchobo.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;


@Repository
public interface GlowDao {

	public int insertPicture(PictureVO paramVO);
	public int checkDuplicationPicture(PictureVO paramVO);
	public int insertPictureChild(PictureChildVO paramVO);

	public ArrayList<PictureVO> selectPicture(GlowVO paramVO);
	public ArrayList<PictureChildVO> selectPictureChild(PictureVO paramVO);
}

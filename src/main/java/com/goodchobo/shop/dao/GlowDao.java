package com.goodchobo.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.goodchobo.common.exception.BusinessException;
import com.goodchobo.common.model.GlowVO;
import com.goodchobo.common.model.PictureChildVO;
import com.goodchobo.common.model.PictureVO;
import com.goodchobo.common.model.PointLogVO;
import com.goodchobo.common.model.TagVO;


@Repository
public interface GlowDao {
	//사진폴더,사진
	public int insertPicture(PictureVO paramVO);
	public int checkDuplicationPicture(PictureVO paramVO);
	public int insertPictureChild(PictureChildVO paramVO);
	public ArrayList<PictureVO> selectPicture(PictureVO paramVO);
	public ArrayList<PictureChildVO> selectPictureChild(PictureVO paramVO);
	//사진태그
	public int insertTag(TagVO paramVO);
	//태그랭킹
	public ArrayList<TagVO> selectPictureTagRanking(TagVO paramVO);
	//폴더생성시 1000포인트
	public GlowVO selectGlow(GlowVO paramVO);
	public int insertGlowPoint(GlowVO paramVO);
	public int insertPointLog(PointLogVO paramVO);
	//포인트사용 통계
	public ArrayList<PictureVO> selectPicturePointStats(PictureVO paramVO);
	public ArrayList<PointLogVO> selectPicturePointDetailStats(PointLogVO paramVO);
	//미사용폴더 통계
	public ArrayList<PointLogVO> selectPictureUnused(PointLogVO paramVO);
}

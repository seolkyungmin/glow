package com.goodchobo.common.model;

import java.util.List;

public class PictureVO{

	private int id;		//PK
	private String name;
	private int userId;
	private List<PictureChildVO> pictureChildList;
	private int pictureCounts;
	private String createdAt;
	private String updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<PictureChildVO> getPictureChildList() {
		return pictureChildList;
	}

	public void setPictureChildList(List<PictureChildVO> pictureChildList) {
		this.pictureChildList = pictureChildList;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPictureCounts() {
		return pictureCounts;
	}

	public void setPictureCounts(int pictureCounts) {
		this.pictureCounts = pictureCounts;
	}

}

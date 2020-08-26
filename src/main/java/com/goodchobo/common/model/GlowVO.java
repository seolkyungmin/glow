package com.goodchobo.common.model;

import java.util.List;


public class GlowVO {
	private int id;		//PK
	private String name;
	private int points;
	private List<PictureVO> pictuerList;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<PictureVO> getPictuerList() {
		return pictuerList;
	}

	public void setPictuerList(List<PictureVO> pictuerList) {
		this.pictuerList = pictuerList;
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

}

package com.goodchobo.common.model;

import java.util.List;


public class PointLogVO {

	private int id;		//PK
	private int userId;
	private int pictureId;
	private String logData;
	private int points;
	private String createdAt;
	private String updatedAt;

	private int plusPoint;
	private int minusPoint;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public String getLogData() {
		return logData;
	}
	public void setLogData(String logData) {
		this.logData = logData;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
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
	public int getPlusPoint() {
		return plusPoint;
	}
	public void setPlusPoint(int plusPoint) {
		this.plusPoint = plusPoint;
	}
	public int getMinusPoint() {
		return minusPoint;
	}
	public void setMinusPoint(int minusPoint) {
		this.minusPoint = minusPoint;
	}

}

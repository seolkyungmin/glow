package com.goodchobo.common.model;

public class TagVO {

	private int id;		//PK
	private String tagName;
	private int pictureChildId;
	private int tagCount;
	private String createdAt;
	private String updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getPictureChildId() {
		return pictureChildId;
	}

	public void setPictureChildId(int pictureChildId) {
		this.pictureChildId = pictureChildId;
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

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

}

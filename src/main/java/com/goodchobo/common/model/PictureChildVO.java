package com.goodchobo.common.model;

import java.util.List;

public class PictureChildVO {

	private int id;		//PK
	private String name;
	private String url;
	private int pictureId;
	private String createdAt;
	private String updatedAt;

	private List<TagVO> tagList;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
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

	public List<TagVO> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagVO> tagList) {
		this.tagList = tagList;
	}

}

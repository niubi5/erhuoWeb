package com.gem.erhuo.entity;

import java.io.File;

public class Markets {
	private int id;
	private String name;
	private int userCount;
	private int goodsCount;
	private String url;
	private String brief;
	
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
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	@Override
	public String toString() {
		return "Markets [id=" + id + ", name=" + name + ", userCount=" + userCount + ", goodsCount=" + goodsCount
				+ ", url=" + url + ", brief=" + brief + "]";
	}
	
}

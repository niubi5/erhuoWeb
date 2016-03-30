package com.gem.erhuo.entity;

import java.util.Date;

public class Helps {
	private int id;
	private int userId;
	private String title;
	private String detail;
	private String pubtime;
	private String logistics;
	private String consignee;
	private String address;
	
	private int state;
	
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	public Helps(){
		
	}
	

	public Helps(int id, int userId, String title, String detail, String pubtime, String logistics, String consignee,
			String address) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.detail = detail;
		this.pubtime = pubtime;
		this.logistics = logistics;
		this.consignee = consignee;
		this.address = address;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPubTime() {
		return pubtime;
	}
	public void setPubTime(String pubTime) {
		this.pubtime = pubTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}

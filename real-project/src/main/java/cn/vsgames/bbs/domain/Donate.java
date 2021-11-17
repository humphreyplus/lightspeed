package cn.vsgames.bbs.domain;

import java.util.Date;

import cn.vsgames.bbs.util.DateFormatter;

public class Donate {
	
	private String username;
	private Integer userId;
	private double money;
	private Date donateTime;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getDonateTime() {
		return donateTime;
	}
	public void setDonateTime(Date donateTime) {
		this.donateTime = donateTime;
	}
	public String getFormatDonateTime() {
		return DateFormatter.formatDate(donateTime);
	}

}

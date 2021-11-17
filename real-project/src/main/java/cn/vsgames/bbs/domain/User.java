package cn.vsgames.bbs.domain;

import java.util.Date;

import cn.vsgames.bbs.util.DateFormatter;

public class User {

	private Integer id;
	private String username;
	private String password;
	private String email;
	
	private Date registerTime;
	private String avatar;
	
	private String gameNickname;
	private String roleName;
	private String areaName;
	private String homelandName;
	private int gw2HomelandId;
	private String organization;
	private int publicStatus;
	
	private int topicCount;
	private int replyCount;

	public int getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}
	public int getGw2HomelandId() {
		return gw2HomelandId;
	}
	public void setGw2HomelandId(int gw2HomelandId) {
		this.gw2HomelandId = gw2HomelandId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getFormatRegisterTime() {
		return DateFormatter.formatDate(registerTime);
	}
	public String getGameNickname() {
		return gameNickname;
	}
	public void setGameNickname(String gameNickname) {
		this.gameNickname = gameNickname;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getHomelandName() {
		return homelandName;
	}
	public void setHomelandName(String homelandName) {
		this.homelandName = homelandName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
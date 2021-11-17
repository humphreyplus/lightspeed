package cn.vsgames.bbs.domain;

import java.util.Date;

import cn.vsgames.bbs.util.DateFormatter;

public class Message {
	
	private int id;
	private int userId;
	private String content;
	private int type;
	private int sendUserId;
	private int replyId;
	private int readStatus;
	private int delStatus;
	private Date createTime;
	private String sendUsername;
	private String topicTitle;
	
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getSendUsername() {
		return sendUsername;
	}
	public void setSendUsername(String sendUsername) {
		this.sendUsername = sendUsername;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(int sendUserId) {
		this.sendUserId = sendUserId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getFormatCreateTime() {
		return DateFormatter.formatShortDate(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

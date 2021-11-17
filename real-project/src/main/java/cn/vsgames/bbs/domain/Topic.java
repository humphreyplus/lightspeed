package cn.vsgames.bbs.domain;

import java.util.Date;

import cn.vsgames.bbs.util.DateFormatter;

public class Topic {
	
	private int id;
	private String title;
	private String image;
	private int sectionId;
	private String sectionName;
	private int userId;
	private String username;
	private String avatar;
	private Date lastReplyTime;
	private int lastReplyUserId;
	private String lastReplyUsername;
	private Date createTime;
	private int replyCount;
	private int topicTypeId;
	private String topicTypeName;
	private int top;
	private int highQuality;
	private int sectionTop;
	private boolean selectSection = false;
	

	public boolean isSelectSection() {
		return selectSection;
	}
	public void setSelectSection(boolean selectSection) {
		this.selectSection = selectSection;
	}
	public int getSectionTop() {
		return sectionTop;
	}
	public void setSectionTop(int sectionTop) {
		this.sectionTop = sectionTop;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getHighQuality() {
		return highQuality;
	}
	public void setHighQuality(int highQuality) {
		this.highQuality = highQuality;
	}
	public int getTopicTypeId() {
		return topicTypeId;
	}
	public void setTopicTypeId(int topicTypeId) {
		this.topicTypeId = topicTypeId;
	}
	public String getTopicTypeName() {
		return topicTypeName;
	}
	public void setTopicTypeName(String topicTypeName) {
		this.topicTypeName = topicTypeName;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getLastReplyTime() {
		return lastReplyTime;
	}
	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	public int getLastReplyUserId() {
		return lastReplyUserId;
	}
	public void setLastReplyUserId(int lastReplyUserId) {
		this.lastReplyUserId = lastReplyUserId;
	}
	public String getLastReplyUsername() {
		return lastReplyUsername;
	}
	public void setLastReplyUsername(String lastReplyUsername) {
		this.lastReplyUsername = lastReplyUsername;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFormatLastReplyTime() {
		return DateFormatter.formatDate(lastReplyTime);
	}
	public String getFormatCreateTime() {
		return DateFormatter.formatDate(createTime);
	}
	public String getImportance() {
		if(top != 0 || (selectSection && sectionTop != 0)) {
			return "置顶";
		} else if(highQuality != 0) {
			return "精品";
		} else if(topicTypeId != 0) {
			return topicTypeName;
		} else {
			return null;
		}
	}
	public String getImportanceColor() {
		if(top != 0 || (selectSection && sectionTop != 0)) {
			return "red";
		} else if(highQuality != 0) {
			return "orange";
		} else if(topicTypeId != 0) {
			return "#888";
		} else {
			return null;
		}
	}
}
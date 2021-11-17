package cn.vsgames.bbs.domain;

import java.util.Set;

public class AtUserResult {
	
	private String newHtml;
	private Set<Integer> userIds;
	public String getNewHtml() {
		return newHtml;
	}
	public void setNewHtml(String newHtml) {
		this.newHtml = newHtml;
	}
	public Set<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(Set<Integer> userIds) {
		this.userIds = userIds;
	}

}

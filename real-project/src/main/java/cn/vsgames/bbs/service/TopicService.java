package cn.vsgames.bbs.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.vsgames.bbs.domain.AtUserResult;
import cn.vsgames.bbs.domain.Message;
import cn.vsgames.bbs.domain.Reply;
import cn.vsgames.bbs.domain.Section;
import cn.vsgames.bbs.domain.Topic;
import cn.vsgames.bbs.domain.Type;
import cn.vsgames.bbs.domain.User;
import cn.vsgames.bbs.init.ApplicationProperties;
import cn.vsgames.bbs.mapper.MessageMapper;
import cn.vsgames.bbs.mapper.TopicMapper;
import cn.vsgames.bbs.mapper.UserMapper;
import cn.vsgames.bbs.util.HtmlUtil;
import cn.vsgames.bbs.util.PagerUtil;

@Service
public class TopicService {
	
	@Autowired
	private TopicMapper topicMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	public Topic getTopicById(Integer id) {
		return topicMapper.getTopicById(id);
	}
	
	public List<Reply> getReplyList(Integer topicId, int p) {
		int pageSize = applicationProperties.getPageSize();
		int startIndex = (p - 1) * pageSize;
		return topicMapper.getReplyList(topicId, startIndex, pageSize);
	}
	
	public int getReplyPageCount(Integer topicId) {
		int pageSize = applicationProperties.getPageSize();
		int count = topicMapper.getReplyCount(topicId);
		return PagerUtil.getPageCount(count, pageSize);
	}
	
	public List<Topic> getTopicList(Integer sectionId, String key, int p) {
		int pageSize = applicationProperties.getPageSize();
		int startIndex = (p - 1) * pageSize;
		return topicMapper.getTopicList(sectionId, key, startIndex, pageSize);
	}
	
	public int getTopicPageCount(Integer sectionId, String key) {
		int pageSize = applicationProperties.getPageSize();
		int count = topicMapper.getTopicCount(sectionId, key);
		return PagerUtil.getPageCount(count, pageSize);
	}
	
	public List<Topic> getHotTopicList() {
		return topicMapper.getHotTopicList();
	}

	@Transactional
	public void insertReply(String content, int userId, int topicId) throws PolicyException, ScanException, IOException {
		AtUserResult atUserResult = atUser(content, userId);
		content = atUserResult.getNewHtml();
		content = HtmlUtil.formatHtml(content);
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setTopicId(topicId);
		reply.setUserId(userId);
		topicMapper.insertReply(reply);
		topicMapper.updateReplyTopic(topicId, userId);
		userMapper.updateReplyUser(userId);
		insertMessage(atUserResult.getUserIds(), reply.getId(), userId);
	}
	
	public List<Section> getSectionList() {
		return topicMapper.getSectionList();
	}

	@Transactional
	public int insertTopic(String content, String title, String image, int sectionId,
			int topicTypeId, int userId) throws PolicyException, ScanException, IOException {
		AtUserResult atUserResult = atUser(content, userId);
		content = atUserResult.getNewHtml();
		content = HtmlUtil.formatHtml(content);
		Topic topic = new Topic();
		topic.setTitle(title);
		topic.setSectionId(sectionId);
		topic.setUserId(userId);
		topic.setImage(image);
		topic.setTopicTypeId(topicTypeId);
		topicMapper.insertTopic(topic);
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setTopicId(topic.getId());
		reply.setUserId(userId);
		topicMapper.insertFirstReply(reply);
		userMapper.updateTopicUser(userId);
		insertMessage(atUserResult.getUserIds(), reply.getId(), userId);
		return topic.getId();
	}

	public List<Type> getTypeList() {
		return topicMapper.getTypeList();
	}

	public List<Topic> getUserNewTopic(Integer userId) {
		return topicMapper.getUserNewTopic(userId);
	}

	public List<Reply> getUserNewReply(Integer userId) {
		return topicMapper.getUserNewReply(userId);
	}
	
	private AtUserResult atUser(String html, int sendUserId) {
		StringBuilder sb = new StringBuilder(html);
		int offset = 0;
		Pattern pattren = Pattern.compile("@[A-Za-z0-9\u4E00-\u9FA5]{2,8}");
		Matcher matcher = pattren.matcher(html);
		Set<Integer> userIds = new HashSet<Integer>();
		while (matcher.find()) {
			String username = matcher.group().substring(1);
			User user = userMapper.getUserByUsername(username);
			if(user != null) {
				String aTag = "<a href='" + applicationProperties.getWebsiteBase()
						+ "/user/" + user.getId()
						+ "' target='_blank'>@" + username + "</a>";
				sb.replace(matcher.start() + offset, matcher.end() + offset, aTag);
				offset += aTag.length() - username.length() - 1;
				userIds.add(user.getId());
			}
		}
		
		AtUserResult atUserResult = new AtUserResult();
		atUserResult.setNewHtml(sb.toString());
		atUserResult.setUserIds(userIds);
		return atUserResult;
	}
	
	private void insertMessage(Set<Integer> userIds, int replyId, int sendUserId) {
		for(int userId : userIds) {
			Message message = new Message();
			message.setUserId(userId);
			message.setSendUserId(sendUserId);
			message.setReplyId(replyId);
			message.setType(2);
			messageMapper.insertMessage(message);
		}
	}

	public int getReplyPagePosition(int replyId) {
		int position = topicMapper.getReplyPosition(replyId);
		int pageSize = applicationProperties.getPageSize();
		return PagerUtil.getPageCount(position, pageSize);
	}

	public int getTopicIdByReplyId(int replyId) {
		return topicMapper.getTopicIdByReplyId(replyId);
	}

	public void deleteReply(int topicId, int userId) {
		topicMapper.deleteReply(topicId, userId);
	}

	public Reply getReplyById(int id) {
		return topicMapper.getReplyById(id);
	}

	public void updateReply(int replyId, int userId, String content) throws PolicyException, ScanException, IOException {
		topicMapper.updateReply(replyId, userId, HtmlUtil.formatHtml(content));
	}

	public void updateTopic(int topicId, int userId, String title) {
		topicMapper.updateTopic(topicId, userId, title);
	}

	public void deleteTopic(int topicId, int userId) {
		topicMapper.deleteTopic(topicId, userId);
	}
}

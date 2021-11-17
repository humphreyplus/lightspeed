package cn.vsgames.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.Message;
import cn.vsgames.bbs.mapper.MessageMapper;
import cn.vsgames.bbs.util.PagerUtil;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	public void insertMessage(Message message) {
		messageMapper.insertMessage(message);
	}
	
	public int getUserUnreadMsgCount(int userId) {
		return messageMapper.getUserUnreadMsgCount(userId);
	}

	public int getMessagePageCount(int userId) {
		int pageSize = Constant.MESSAGE_PAGE_SIZE;
		int count = messageMapper.getMessageCount(userId);
		return PagerUtil.getPageCount(count, pageSize);
	}

	public List<Message> getMessageList(int userId, int p) {
		int pageSize = Constant.MESSAGE_PAGE_SIZE;
		int startIndex = (p - 1) * pageSize;
		return messageMapper.getMessageList(userId, startIndex, pageSize);
	}

	public void readMessage(int userId, int messageId) {
		messageMapper.updateReadStatus(userId, messageId);
	}
}

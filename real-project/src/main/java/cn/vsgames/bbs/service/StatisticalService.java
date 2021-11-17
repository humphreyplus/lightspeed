package cn.vsgames.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vsgames.bbs.domain.StatisticalData;
import cn.vsgames.bbs.mapper.TopicMapper;
import cn.vsgames.bbs.mapper.UserMapper;

@Service
public class StatisticalService {
	
	@Autowired
	private TopicMapper topicMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public StatisticalData getStatisticalData() {
		StatisticalData statisticalData = new StatisticalData();
		statisticalData.setTopicCount(topicMapper.getTopicCount(null, null));
		statisticalData.setReplyCount(topicMapper.getReplyCount(null));
		statisticalData.setUserCount(userMapper.getUserCount());
		return statisticalData;
	}

}

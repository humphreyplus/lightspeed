package cn.vsgames.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.vsgames.bbs.domain.Gw2Area;
import cn.vsgames.bbs.domain.Gw2Homeland;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.domain.User;
import cn.vsgames.bbs.init.ApplicationProperties;
import cn.vsgames.bbs.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	public LoginUser login(String user, String password) {
		LoginUser loginUser = null;
		if(user.contains("@")) {
			loginUser = userMapper.loginByEmail(user, password);
		} else {
			loginUser = userMapper.loginByUsername(user, password);
		}
		return loginUser;
	}
	
	public int register(String username, String password, String email) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		user.setEmail(email);
		user.setAvatar(applicationProperties.getDefaultAvatarHttpUrl());
		userMapper.insert(user);
		return user.getId();
	}

	public LoginUser getLoginUserById(int userId) {
		return userMapper.getLoginUserById(userId);
	}

	public int getUserCountByUsername(String username) {
		return userMapper.getUserCountByUsername(username);
	}
	
	public int getUserCountByEmail(String email) {
		return userMapper.getUserCountByEmail(email);
	}

	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

	public List<Gw2Area> getGw2AreaList() {
		return userMapper.getGw2AreaList();
	}

	public List<Gw2Homeland> getGw2HomelandList() {
		return userMapper.getGw2HomelandList();
	}

	public void submitGw2Info(User user) {
		userMapper.submitGw2Info(user);
	}

	public void updateUserAvatar(int id, String avatar) {
		userMapper.updateUserAvatar(id, avatar);
	}

	public String getEmailByResetPwdKey(String key) {
		return userMapper.getEmailByResetPwdKey(key);
	}

	@Transactional
	public void updatePwd(String key, String email, String password) {
		userMapper.updateResetPwdStatus(key, 2);
		userMapper.updatePwd(email, password);
	}

	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}
	
}
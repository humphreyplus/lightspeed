package cn.vsgames.bbs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import cn.vsgames.bbs.init.ApplicationProperties;
import cn.vsgames.bbs.mapper.UserMapper;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	public void sendSetPwdEmail(String email) throws MessagingException {
		
		String uuid = UUID.randomUUID().toString();
		
		userMapper.insertResetPwdData(email, uuid);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("linkurl", applicationProperties.getWebsiteBase() + "/resetpwd?key=" + uuid);
		String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "email/resetpwd.vm", "UTF-8", model);
		System.out.println(text);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setFrom("VSGAMES <webmaster@vsgames.cn>");
		helper.setTo(email);
		helper.setSubject("[VSGAMES]密码重置");
		helper.setText(text, true);
		mailSender.send(message);
		
		userMapper.updateResetPwdStatus(uuid, 1);
	}
}

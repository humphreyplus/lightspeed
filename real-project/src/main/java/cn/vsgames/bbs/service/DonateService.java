package cn.vsgames.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vsgames.bbs.domain.Donate;
import cn.vsgames.bbs.mapper.DonateMapper;

@Service
public class DonateService {
	
	@Autowired
	private DonateMapper donateMapper;
	
	public List<Donate> getDonateList() {
		return donateMapper.getDonateList();
	}

}

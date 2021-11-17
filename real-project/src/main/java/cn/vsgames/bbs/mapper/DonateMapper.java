package cn.vsgames.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.vsgames.bbs.domain.Donate;

public interface DonateMapper {
	
	@Select("select u.username,u.id as userId,d.money,d.donate_time as donateTime from t_donate d left join t_user u on d.user_id=u.id order by d.donate_time desc")
	public List<Donate> getDonateList();
	
}

package cn.vsgames.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.vsgames.bbs.domain.Gw2Area;
import cn.vsgames.bbs.domain.Gw2Homeland;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.domain.User;

public interface UserMapper {
	
	@Select("select id,username,avatar from t_user where username = #{username} and password = #{password}")
	public LoginUser loginByUsername(@Param("username") String username, @Param("password") String password);
	
	@Select("select id,username,avatar from t_user where email = #{email} and password = #{password}")
	public LoginUser loginByEmail(@Param("email") String email, @Param("password") String password);

	@Select("select count(*) from t_user")
	public int getUserCount();

	public void insert(User user);

	@Select("select id,username,avatar from t_user where id = #{userId}")
	public LoginUser getLoginUserById(int userId);

	@Select("select count(*) from t_user where username = #{username}")
	public int getUserCountByUsername(String username);
	
	@Select("select count(*) from t_user where email = #{email}")
	public int getUserCountByEmail(String email);

	@Update("update t_user set reply_count=reply_count+1 where id=#{userId}")
	public void updateReplyUser(int userId);

	@Update("update t_user set topic_count=topic_count+1 where id=#{userId}")
	public void updateTopicUser(int userId);

	@Select("select u.id,u.username,u.avatar,u.topic_count as topicCount,u.reply_count as replyCount,"
			+ "u.register_time as registerTime,gu.game_nickname as gameNickname,gu.role_name as roleName,"
			+ "gu.organization,gu.public_status as publicStatus,a.name as areaName,h.name as homelandName"
			+ " from t_user u left join t_gw2_user_info gu on u.id=gu.user_id"
			+ " left join t_gw2_homeland h on h.id=gu.gw2_homeland_id"
			+ " left join t_gw2_area a on a.id=h.gw2_area_id where u.id=#{userId}")
	public User getUserById(Integer userId);

	@Select("select id,name from t_gw2_area")
	public List<Gw2Area> getGw2AreaList();

	@Select("select id,name,gw2_area_id as gw2AreaId from t_gw2_homeland")
	public List<Gw2Homeland> getGw2HomelandList();

	@Insert("replace into t_gw2_user_info (user_id,gw2_homeland_id,organization,game_nickname,role_name,public_status) "
			+ " values (#{user.id},#{user.gw2HomelandId},#{user.organization},#{user.gameNickname},#{user.roleName},#{user.publicStatus})")
	public void submitGw2Info(@Param("user") User user);

	@Update("update t_user set avatar=#{avatar} where id=#{id}")
	public void updateUserAvatar(@Param("id") int id, @Param("avatar") String avatar);

	@Insert("insert into t_reset_pwd (uuid,email,create_time) values (#{uuid},#{email},now())")
	public void insertResetPwdData(@Param("email") String email, @Param("uuid") String uuid);

	@Update("update t_reset_pwd set status=#{status} where uuid=#{uuid}")
	public void updateResetPwdStatus(@Param("uuid") String uuid, @Param("status") int status);

	@Select("select email from t_reset_pwd where uuid=#{key} and status=1 and create_time>DATE_SUB(now(),INTERVAL 1 HOUR)")
	public String getEmailByResetPwdKey(@Param("key") String key);

	@Update("update t_user set password=#{password} where email=#{email}")
	public void updatePwd(@Param("email") String email, @Param("password") String password);

	@Select("select id from t_user where username = #{username}")
	public User getUserByUsername(String username);
}

package cn.vsgames.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.vsgames.bbs.domain.Message;

public interface MessageMapper {
	
	@Insert("insert into t_message (user_id,content,send_user_id,reply_id,type,create_time)"
			+ " values (#{userId},#{content},#{sendUserId},#{replyId},#{type},now())")
	public void insertMessage(Message message);

	@Select("select count(*) from t_message where read_status=0 and del_status=0 and user_id=#{userId}")
	public int getUserUnreadMsgCount(@Param("userId") int userId);

	@Select("select count(*) from t_message where user_id=#{userId} and del_status=0")
	public int getMessageCount(@Param("userId") int userId);

	@Select("select m.id,m.content,m.send_user_id as sendUserId,m.reply_id as replyId,m.type,t.title as topicTitle,"
			+ "m.read_status as readStatus,m.create_time as createTime,u.username as sendUsername"
			+ " from t_message m left join t_user u on m.send_user_id=u.id"
			+ " left join t_reply r on m.reply_id=r.id left join t_topic t on r.topic_id=t.id"
			+ " where m.user_id=#{userId} and m.del_status=0 order by m.read_status asc,m.create_time desc limit #{startIndex},#{pageSize}")
	public List<Message> getMessageList(@Param("userId") int userId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

	@Update("update t_message set read_status=1 where id=#{messageId} and user_id=#{userId}")
	public void updateReadStatus(@Param("userId") int userId, @Param("messageId") int messageId);
	
}

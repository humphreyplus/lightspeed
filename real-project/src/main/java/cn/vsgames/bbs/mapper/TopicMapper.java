package cn.vsgames.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.vsgames.bbs.domain.Reply;
import cn.vsgames.bbs.domain.Section;
import cn.vsgames.bbs.domain.Topic;
import cn.vsgames.bbs.domain.Type;

public interface TopicMapper {
	
	@Select("select t.id,t.title,t.top,t.section_top as sectionTop,t.high_quality as highQuality,t.image,t.reply_count as replyCount,"
			+ "t.user_id as userId,t.topic_type_id as topicTypeId,p.name as topicTypeName,t.section_id as sectionId"
			+ " from t_topic t left join t_topic_type p on p.id = t.topic_type_id"
			+ " where t.id=#{id} and t.del_status=0")
	public Topic getTopicById(Integer id);
	
	@Select("select r.id,r.content,r.floor,u.username,u.avatar,r.reply_time as replyTime,r.user_id as userId,"
			+ "r.update_time as updateTime from t_reply r inner join t_user u on r.user_id=u.id"
			+ " where r.topic_id=#{topicId} and r.del_status=0 order by r.floor asc limit #{startIndex},#{pageSize}")
	public List<Reply> getReplyList(@Param("topicId") Integer topicId, 
			@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
	
	public int getReplyCount(@Param("topicId") Integer topicId);
	
	public List<Topic> getTopicList(@Param("sectionId") Integer sectionId, @Param("key") String key,
			@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
	
	public int getTopicCount(@Param("sectionId") Integer sectionId, @Param("key") String key);
	
	@Select("select id,title,reply_count as replyCount from t_topic"
			+ " where create_time > date_add(now(), interval -3 day) and del_status = 0 order by reply_count desc limit 5")
	public List<Topic> getHotTopicList();

	public void insertReply(Reply reply);
	
	public void insertFirstReply(Reply reply);

	@Update("update t_topic set last_reply_time=now(),last_reply_user_id=#{userId},reply_count=reply_count+1 where id=#{topicId}")
	public void updateReplyTopic(@Param("topicId") int topicId, @Param("userId") int userId);

	@Select("select id,name,introduce from t_section order by order_index asc")
	public List<Section> getSectionList();

	public void insertTopic(Topic topic);

	@Select("select id,name from t_topic_type")
	public List<Type> getTypeList();

	@Select("select id,create_time as createTime,title from t_topic where del_status=0 and user_id=#{userId}"
			+ " order by create_time desc limit 5")
	public List<Topic> getUserNewTopic(Integer userId);

	@Select("select r.id as id,t.title as topicTitle,r.reply_time as replyTime from t_topic t inner join t_reply r on r.topic_id=t.id where t.del_status=0 and r.del_status=0"
			+ " and r.user_id=#{userId} and r.floor!=1 order by r.reply_time desc limit 5")
	public List<Reply> getUserNewReply(Integer userId);

	@Select("select count(*)+1 from t_reply where id<#{replyId} and topic_id=(select topic_id from t_reply where id=#{replyId}) and del_status=0")
	public int getReplyPosition(@Param("replyId") int replyId);

	@Select("select topic_id from t_reply where id=#{replyId} and del_status=0")
	public int getTopicIdByReplyId(@Param("replyId") int replyId);

	@Update("update t_reply set del_status=1 where id=#{topicId} and user_id=#{userId}")
	public void deleteReply(@Param("topicId") int topicId, @Param("userId") int userId);

	@Select("select r.id,r.content,r.user_id as userId"
			+ " from t_reply r"
			+ " where r.id=#{id} and r.del_status=0")
	public Reply getReplyById(int id);

	@Update("update t_reply set content=#{content},update_time=now() where id=#{replyId} and user_id=#{userId}")
	public void updateReply(@Param("replyId") int replyId, @Param("userId") int userId, @Param("content") String content);

	@Update("update t_topic set title=#{title} where id=#{topicId} and user_id=#{userId}")
	public void updateTopic(@Param("topicId") int topicId, @Param("userId") int userId, @Param("title") String title);

	@Update("update t_topic set del_status=1 where id=#{topicId} and user_id=#{userId}")
	public void deleteTopic(@Param("topicId") int topicId, @Param("userId") int userId);
	
}

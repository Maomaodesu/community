package life.maomao.community.mapper;

import life.maomao.community.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicMapper {
    @Insert("insert into topic(title,description,gmt_create,gmt_modified,creator_id,comment_count,view_count,like_count,dislike_count,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creatorId},#{commentCount},#{viewCount},#{likeCount},#{dislikeCount},#{tag})")
    void createTopic(Topic topic);

    @Select("select * from topic")
    List<Topic> getDefaultTopicList();

    @Select("select * from topic where creator_id = #{userId}")
    List<Topic> getDefaultTopicListByUserId(@Param("userId") Integer userId);

//    @Select("select count(1) from topic")
//    Integer getTopicCount();

    @Select("select * from topic where id = #{id}")
    Topic getTopicByTopicId(@Param("id") Integer id);
}

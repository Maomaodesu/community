package life.maomao.community.mapper;

import life.maomao.community.model.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Maomao on 2020/6/27
 */
@Mapper
public interface TopicSelectAllMapper {
    @Select("select * from topic")
    List<Topic> selectAll();

    @Select("select * from topic where creator_id = #{creatorId}")
    List<Topic> selectAllByCreatorId(@Param("creatorId") Integer creatorId);
}

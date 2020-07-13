package life.maomao.community.mapper;

import life.maomao.community.model.Topic;
import life.maomao.community.model.TopicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicExtMapper {
    int incView(Topic record);

    int incCommentCount(Topic record);

    int incLike(Topic record);

    int incDisLike(Topic record);
}
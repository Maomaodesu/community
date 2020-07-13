package life.maomao.community.service;

import life.maomao.community.enums.CommentTypeEnum;
import life.maomao.community.exception.CustomizeErrorCode;
import life.maomao.community.exception.CustomizeException;
import life.maomao.community.mapper.CommentMapper;
import life.maomao.community.mapper.TopicExtMapper;
import life.maomao.community.mapper.TopicMapper;
import life.maomao.community.model.Comment;
import life.maomao.community.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maomao on 2020/7/8
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicExtMapper topicExtMapper;

    //把整个方法体变为一个事务
    @Transactional
    public void createOrUpdate(Comment comment){
//        //在数据库中根据这个id进行查找
//        CommentExample commentExampleForSelect  = new CommentExample();
//        commentExampleForSelect.createCriteria()
//                .andIdEqualTo(comment.getId());
//        //根据id定位评论
//        List<Comment> commentInDBList = commentMapper.selectByExample(commentExampleForSelect);
//        //如果评论不存在，则使用新创建的comment，补全这个评论的初始信息
//        if(commentInDBList.size() == 0){
//            //记录当前创建时间
//            comment.setGmtCreate(System.currentTimeMillis());
//            //记录当前修改时间
//            comment.setGmtModified(comment.getGmtCreate());
//            //记录点赞数
//            comment.setLikeCount((long)0);
//            //记录点踩数
//            comment.setDislikeCount((long)0);
//        }
    //对评论出现的异常进行处理
        //父评论id为空||评论类型是0，说明：未选中任何主题或评论进行回复
        if(comment.getParentId() == null || comment.getType() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //评论类型是空||评论类型是评论类型枚举类中未定义的数字，说明：评论类型错误或不存在
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        //评论类型是评论类型枚举类中定义的COMMENT（评论）数字，即评论的对象是评论
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOTFOUND);
            }
            commentMapper.insert(comment);
        } else { //评论类型是评论类型枚举类中定义的TOPIC（主题）数字，即评论的对象是主题
            //回复主题
            Topic topic = topicMapper.selectByPrimaryKey(comment.getParentId());
            if(topic == null){
                throw new CustomizeException(CustomizeErrorCode.TOPIC_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //给主题的评论数+1
            topic.setCommentCount((long) 1);
            topicExtMapper.incCommentCount(topic);
        }
    }
}

package life.maomao.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.maomao.community.dto.TopicDTO;
import life.maomao.community.exception.CustomizeErrorCode;
import life.maomao.community.exception.CustomizeException;
import life.maomao.community.mapper.TopicExtMapper;
import life.maomao.community.mapper.TopicMapper;
import life.maomao.community.mapper.UserMapper;
import life.maomao.community.model.Topic;
import life.maomao.community.model.TopicExample;
import life.maomao.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Maomao on 2020/6/18
 */
@Service
public class TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicExtMapper topicExtMapper;
    //首页帖子分页
    public PageInfo<Topic> getDefaultTopicList(Integer pageNum, Integer pageSize) {
        TopicExample topicExample = new TopicExample();
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    //我的发帖分页
    public PageInfo<Topic> getDefaultTopicListByUserId(Long userId, Integer pageNum, Integer pageSize) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andCreatorIdEqualTo(Long.valueOf(userId));
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        if(topicList.size() != 0) {
            PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
            return pageInfo;
        } else {
            return null;
        }
    }

    //点击帖子进入
    public TopicDTO getTopicDTOByTopicId(Long id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        if(topic == null){ //帖子被删了
            throw new CustomizeException(CustomizeErrorCode.TOPIC_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(topic.getCreatorId());
        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic, topicDTO);
        topicDTO.setUser(user);
        return topicDTO;
    }

    //新建或更新帖子
    public void createOrUpdate(Topic topic) {
        //如果id = null，说明是新建的主题，补全这个主题的初始信息并执行创建，否则说明是修改的主题
        if(topic.getId() == null){
            //创建时间
            topic.setGmtCreate(System.currentTimeMillis());
            //修改时间
            topic.setGmtModified(topic.getGmtCreate());
            //浏览数
            topic.setViewCount((long) 0);
            //评论数
            topic.setCommentCount((long) 0);
            //点赞数
            topic.setLikeCount((long) 0);
            //点踩数
            topic.setViewCount((long) 0);

            //执行创建
            topicMapper.insertSelective(topic);
        } else { //帖子存在，则更新内容
            //在数据库中根据这个id进行查找
            TopicExample topicExampleForSelect  = new TopicExample();
            topicExampleForSelect.createCriteria()
                    .andIdEqualTo(topic.getId());
            //根据id定位帖子
            List<Topic> topicInDBList = topicMapper.selectByExample(topicExampleForSelect);
            if (topicInDBList.size() == 0) {
                //这个时候帖子被删了
                throw new CustomizeException(CustomizeErrorCode.TOPIC_NOT_FOUND);
            } else {
                Topic topicInDB = topicInDBList.get(0);
                //创建修改的那条数据所对应的对象
                Topic UpdateTopicInDB = new Topic();
                //更新修改时间
                UpdateTopicInDB.setGmtModified(System.currentTimeMillis());
                //更新主题贴头
                UpdateTopicInDB.setTitle(topic.getTitle());
                //更新主题贴内容
                UpdateTopicInDB.setDescription(topic.getDescription());
                //更新主题标签
                System.out.println(topic.getTag());
                UpdateTopicInDB.setTag(topic.getTag());

                //执行更新
                TopicExample topicExampleForUpdate = new TopicExample();
                topicExampleForUpdate.createCriteria()
                        .andIdEqualTo(topicInDB.getId());
                int update = topicMapper.updateByExampleSelective(UpdateTopicInDB, topicExampleForUpdate);
                //如果更新失败（比如更新的时候这帖子被删了），跳转到不存在该贴
                if (update != 1) {
                    throw new CustomizeException(CustomizeErrorCode.TOPIC_NOT_FOUND);
                }
            }
        }
    }

    //浏览次数
    public void incView(Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setViewCount((long) 1);
        topicExtMapper.incView(topic);
    }

    //点赞次数
    public void incLike(Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setLikeCount((long) 1);
        topicExtMapper.incLike(topic);
    }

    //点踩次数
    public void incDisLike(Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setDislikeCount((long) 1);
        topicExtMapper.incDisLike(topic);
    }
}



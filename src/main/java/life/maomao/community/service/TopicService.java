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
//放弃查询发帖用户头像
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicExtMapper topicExtMapper;
    //首页分页
    public PageInfo<Topic> getDefaultTopicList(Integer pageNum, Integer pageSize) {
        TopicExample topicExample = new TopicExample();
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    //我的发帖分页
    public PageInfo<Topic> getDefaultTopicListByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria()
                .andCreatorIdEqualTo(userId);
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topicList = topicMapper.selectByExampleWithBLOBs(topicExample);
        if(topicList.size() != 0) {
            PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
            return pageInfo;
        } else {
            return null;
        }
    }

    public TopicDTO getTopicDTOByTopicId(Long id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        if(topic == null){ //跳转到一个不存在的帖子（可能之前存在）
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(topic.getCreatorId());
        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic, topicDTO);
        topicDTO.setUser(user);
        return topicDTO;
    }

    public void createOrUpdate(Topic topic) {
        TopicExample topicExampleForSelect  = new TopicExample();
        topicExampleForSelect.createCriteria()
                .andIdEqualTo(topic.getId());

        List<Topic> topicInDBList = topicMapper.selectByExample(topicExampleForSelect);
        if(topicInDBList.size() != 0){
            //创建时间
            topic.setGmtCreate(System.currentTimeMillis());
            //修改时间
            topic.setGmtModified(topic.getGmtCreate());
            topicMapper.insert(topic);
        } else {
            Topic topicInDB = topicInDBList.get(0);
            Topic UpdateTopicInDB = new Topic();
            //修改时间
            UpdateTopicInDB.setGmtModified(System.currentTimeMillis());
            //主题贴头
            UpdateTopicInDB.setTitle(topic.getTitle());
            //主题贴内容
            UpdateTopicInDB.setDescription(topic.getDescription());
            //主题标签
            UpdateTopicInDB.setTag(topic.getTag());
            TopicExample topicExampleForUpdate = new TopicExample();
            topicExampleForUpdate.createCriteria()
                    .andIdEqualTo(topicInDB.getId());
            int updated = topicMapper.updateByExampleSelective(UpdateTopicInDB,topicExampleForUpdate);
            if(updated != 1){//在更新的时候跳转到一个不存在的帖子（可能之前存在）
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    //查看浏览次数
    public void incView(Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setViewCount(1);
        topicExtMapper.incView(topic);
    }
}



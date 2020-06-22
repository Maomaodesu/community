package life.maomao.community.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.maomao.community.dto.TopicDTO;
import life.maomao.community.mapper.TopicMapper;
import life.maomao.community.mapper.UserMapper;
import life.maomao.community.model.Topic;
import life.maomao.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    public PageInfo<TopicDTO> getDefaultTopicList(Integer page, Integer size) {
    public PageInfo<Topic> getDefaultTopicList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topics = topicMapper.getDefaultTopicList();
//        List<TopicDTO> topicDTOList = new ArrayList<>();
//        for (Topic topic : topics) {
//            System.out.println(topic);
//            User user = userMapper.findById(topic.getCreatorId());
//            TopicDTO topicDTO = new TopicDTO();
//            //把topic COPY到 topicDTO
//            BeanUtils.copyProperties(topic,topicDTO);
//            topicDTO.setUser(user);
//            topicDTOList.add(topicDTO);
//        }
//        PageInfo<TopicDTO> pageInfo = new PageInfo<>(topicDTOList);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
//        System.out.println("当前页: "+pageInfo.getPageNum());
//        System.out.println("每页显示条数: "+pageInfo.getPageSize());
//        System.out.println("上一页: "+pageInfo.getPrePage());
        return pageInfo;
    }

    public PageInfo<Topic> getDefaultTopicListByUserId(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topics = topicMapper.getDefaultTopicListByUserId(id);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        return pageInfo;
    }


    public TopicDTO getTopicDTOByTopicId(Integer id) {
        Topic topic = topicMapper.getTopicByTopicId(id);
        User user = userMapper.getUserByUserId(topic.getCreatorId());
        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic,topicDTO);
        topicDTO.setUser(user);
        return topicDTO;
    }
}

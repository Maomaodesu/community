package life.maomao.community.controller;

import life.maomao.community.mapper.TopicMapper;
import life.maomao.community.mapper.UserMapper;
import life.maomao.community.model.Topic;
import life.maomao.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maomao on 2020/6/17
 */
@Controller
public class PublishController {

    @Autowired
    private TopicMapper topicMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setDescription(description);
        topic.setTag(tag);
        topic.setCreatorId(user.getId());
        topic.setGmtCreate(System.currentTimeMillis());
        topic.setGmtModified(topic.getGmtCreate());
        topicMapper.createTopic(topic);
        return "redirect:/";
    }
}

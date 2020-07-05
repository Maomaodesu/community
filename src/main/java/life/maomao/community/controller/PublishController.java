package life.maomao.community.controller;

import life.maomao.community.dto.TopicDTO;
import life.maomao.community.mapper.TopicMapper;
import life.maomao.community.model.Topic;
import life.maomao.community.model.User;
import life.maomao.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maomao on 2020/6/17
 */
@Controller
public class PublishController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        TopicDTO topicDTO = topicService.getTopicDTOByTopicId(id);
        model.addAttribute("title",topicDTO.getTitle());
        model.addAttribute("description",topicDTO.getDescription());
        model.addAttribute("tag",topicDTO.getTag());
        model.addAttribute("id", topicDTO.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
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
        topic.setId(id);
        topicService.createOrUpdate(topic);
        return "redirect:/";
    }
}

package life.maomao.community.controller;

import life.maomao.community.dto.TopicDTO;
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
 * 发主题帖
 * Created by Maomao on 2020/6/17
 */
@Controller
public class PublishTopicController {

    @Autowired
    private TopicService topicService;

    //根据id获取当前的topic然后回显，写到publish页面上
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

    //点击发布跳转到发布页面
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    //点击页面上发布按钮，执行发布
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

        //新建主题，填写内容信息，初始化信息在service层
        Topic topic = new Topic();
        topic.setId(id);//如果是修改，则会从publish/id里拿到id，否则是新建，id为空
        topic.setTitle(title);
        topic.setDescription(description);
        topic.setTag(tag);
        System.out.println(topic.getTag());
        topic.setCreatorId(user.getId());
        topicService.createOrUpdate(topic);
        return "redirect:/";
    }
}

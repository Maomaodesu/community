package life.maomao.community.controller;

import com.github.pagehelper.PageInfo;
import life.maomao.community.model.Topic;
import life.maomao.community.model.User;
import life.maomao.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 侧边栏
 * Created by Maomao on 2020/6/19
 */
@Controller
public class ProfileController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("topics".equals(action)){
            model.addAttribute("section","topics");
            model.addAttribute("sectionName","大字报");
        } else if("replies".equals(action)) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","鸡毛信");
        }
        PageInfo<Topic> defaultTopicByUserIdList = topicService.getDefaultTopicListByUserId(user.getId(), pageNum, pageSize);
        model.addAttribute("pageInfoOfTopics",defaultTopicByUserIdList);
        return "profile";
    }
}
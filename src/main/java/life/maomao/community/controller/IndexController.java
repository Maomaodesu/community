package life.maomao.community.controller;

import com.github.pagehelper.PageInfo;
import life.maomao.community.model.Topic;
import life.maomao.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 首页展示
 * Created by Maomao on 2020/6/14
 */
@Controller
public class IndexController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){
//        PageInfo<TopicDTO> pageInfo = topicService.getDefaultTopicList(pageNum, pageSize);
        PageInfo<Topic> pageInfo = topicService.getDefaultTopicList(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}

package life.maomao.community.controller;

import life.maomao.community.dto.TopicDTO;
import life.maomao.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/topic/{id}")
    public String topic(@PathVariable(name = "id")Long id,
                        Model model){
        TopicDTO topicDTO = topicService.getTopicDTOByTopicId(id);
        topicService.incView(id);
        model.addAttribute("topicDTO", topicDTO);
        return "topic";
    }
}

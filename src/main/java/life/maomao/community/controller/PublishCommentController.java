package life.maomao.community.controller;

import life.maomao.community.dto.CommentDTO;
import life.maomao.community.dto.ResultDTO;
import life.maomao.community.exception.CustomizeErrorCode;
import life.maomao.community.mapper.CommentMapper;
import life.maomao.community.model.Comment;
import life.maomao.community.model.User;
import life.maomao.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 发评论
 * Created by Maomao on 2020/7/5
 */
/*
    JSON结构：
     {
        "parentId:16",
        "content":"这是一个回复内容",
        "type":1
     }
 */
@Controller
public class PublishCommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
    //如果用户未登录
        if(user == null){
            //返回用户未登录
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
    //如果用户登录了
        //创建新评论
        Comment comment = new Comment();
        //从commentDTO中获取父评论id
        comment.setParentId(commentDTO.getParentId());
        //从commentDTO中获取评论内容
        comment.setContent(commentDTO.getContent());
        //从commentDTO中获取评论类型
        comment.setType(commentDTO.getType());
        //记录评论者
        comment.setCommenter(user.getId());
        //记录当前创建时间
        comment.setGmtCreate(System.currentTimeMillis());
        //记录当前修改时间
        comment.setGmtModified(comment.getGmtCreate());
        //记录点赞数
        comment.setLikeCount((long)0);
        //记录点踩数
        comment.setDislikeCount((long)0);

        //执行创建
        commentService.createOrUpdate(comment);
        return ResultDTO.okOf();
    }
}

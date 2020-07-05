package life.maomao.community.advice;

import life.maomao.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * created by Maomao on 2020/7/1.
 */
//做通用异常处理
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model){
        if(e instanceof CustomizeException){//该异常可处理
            model.addAttribute("message",e.getMessage());
        }else { //该异常处理不了
            model.addAttribute("message","当我打出？的时候，不是我有问题，是我觉得你有问题");
        }
        return new ModelAndView("error");
    }
}

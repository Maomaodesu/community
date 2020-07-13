package life.maomao.community.advice;

import com.alibaba.fastjson.JSON;
import life.maomao.community.dto.ResultDTO;
import life.maomao.community.exception.CustomizeErrorCode;
import life.maomao.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 捕获并且处理异常
 * created by Maomao on 2020/7/1.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response){
        String contentType = request.getContentType();
        //捕获的异常如果是application/json的方式
        if("application/json".equals(contentType)){
            //contentType内容等于json数据格式，则返回JSON
            ResultDTO resultDTO = null;
            //返回JSON
            if(e instanceof  CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else {//捕获的异常是错误的页面跳转，text.html方式
            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else { //该异常处理不了
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}

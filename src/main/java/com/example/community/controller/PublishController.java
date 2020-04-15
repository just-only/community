package com.example.community.controller;

import com.example.community.demo.Question;
import com.example.community.demo.User;
import com.example.community.mappr.QuestionMapper;
import com.example.community.mappr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/24 11:53
 * Modified By:
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;


    public User getUser(HttpServletRequest request){
        User user = null;
        user = (User) request.getSession().getAttribute("user");
        return user;
    }

    @GetMapping("/publish")
    public String doPublish(){
        return "publish";
    }

    /*
     * @Description: publish的post请求处理方法
     * @Author: weidongya
     * @Date: 2020/3/25 10:19
     * @param tag    问题标签
     * @param description  问题描述
     * @param title  问题标题
     * @param request
     * @param model
     * @result: String
     **/
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "title",required = false) String title,
            HttpServletRequest request,
            Model model){

        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        if(title.isEmpty()) { model.addAttribute("error","标题为空"); return "publish"; }
        if(description.isEmpty()) { model.addAttribute("error","问题描述为空"); return "publish"; }
        if(tag.isEmpty()) { model.addAttribute("error","标签为空！"); return "publish"; }

        User user = getUser(request);

        System.out.println(user);
        if(user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }else{
         //   System.out.println(user);
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreate_time(System.currentTimeMillis());
            question.setModified_time(question.getCreate_time());
            question.setCreator(Integer.valueOf(user.getAccount_id()));
            questionMapper.addQuestion(question);
            return "redirect:/";
        }
    }
}

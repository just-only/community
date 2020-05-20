package com.example.community.controller;

import com.example.community.demo.Question;
import com.example.community.demo.QuestionExample;
import com.example.community.demo.User;
import com.example.community.dto.QuestionDto;
import com.example.community.dto.VisitCommentDto;
import com.example.community.exception.MyException;
import com.example.community.exception.QuestionExceptionMessage;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.service.CommentService;
import com.example.community.service.QuestionDtoService;
import com.example.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020
 * FileName: QuestionController
 * Author:   wdy
 * Date:     2020/4/10 11:19
 * Description: 问题详尽内容界面控制器
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionDtoService questionDtoService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionExtMapper questionExtMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,
                           Model model){
        QuestionDto questionDto = questionDtoService.findById(id);
        questionDtoService.intViewCount(id);
        List<VisitCommentDto> visitCommentDtos = new ArrayList<VisitCommentDto>();
        visitCommentDtos = commentService.getComments(id);
        List<Question> questionsLikes = questionService.findByTag(questionDto.getQuestion().getTag());
        model.addAttribute("questionsLike",questionsLikes);
        model.addAttribute("visitCommentDtos",visitCommentDtos);
        model.addAttribute("questiondto",questionDto);
        return "question";
    }

    public User getUser(HttpServletRequest request){
        User user = null;
        user = (User) request.getSession().getAttribute("user");
        return user;
    }

    @GetMapping("/updatequestion/{id}")
    public String updateQuestion(@PathVariable(name="id") Integer id,
                                 HttpServletRequest request,
                                 Model model){
        Question question = new Question();
        question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new MyException(QuestionExceptionMessage.Question_No_Found);
        }
        String tag = question.getTag();
        String title = question.getTitle();
        String description = question.getDescription();
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("id",id);
        return "updatequestion";
    }

    @PostMapping("/updatequestion/{id}")
    public String doPublish(
            @PathVariable(name="id") Integer id,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "title",required = false) String title,
            HttpServletRequest request,
            Model model){

        Question question = new Question();
        question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new MyException(QuestionExceptionMessage.Question_No_Found);
        }
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        if(title.isEmpty()) { model.addAttribute("error1","标题为空"); return "redirect:/updatequestion"+"/"+id; }
        if(description.isEmpty()) { model.addAttribute("error1","问题描述为空"); return "redirect:/updatequestion"+"/"+id; }
        if(tag.isEmpty()) { model.addAttribute("error1","标签为空！"); return "redirect:/updatequestion"+"/"+id; }

        User user = getUser(request);

        if(user == null) {
            model.addAttribute("error2", "用户未登录！");
            return "redirect:/updatequestion"+"/"+id;
        }else{
            Question question1 = new Question();
            question1.setDescription(description);
            question1.setTag(tag);
            question1.setTitle(title);
            question1.setModifiedTime(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(id);
            int status = questionMapper.updateByExampleSelective(question1,questionExample);
            if(status!=1){
                throw new MyException(QuestionExceptionMessage.Question_No_Found);
            }
            return "redirect:/question"+"/"+id;
        }
    }

    @ResponseBody
    @PostMapping("/question/giveup")
    public String addGiveUp(@RequestParam(name="id") String id){
        Question question = questionMapper.selectByPrimaryKey(Integer.valueOf(id));
        if(question==null){
            throw  new MyException(QuestionExceptionMessage.Question_No_Found);
        }
        questionExtMapper.addLike(question);
        return "sucess";
    }
}

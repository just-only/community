package com.example.community.controller;

import com.example.community.demo.NoticeExample;
import com.example.community.demo.QuestionExample;
import com.example.community.demo.User;
import com.example.community.dto.NoticeDto;
import com.example.community.dto.PageDto;
import com.example.community.dto.QuestionDto;
import com.example.community.exception.MyException;
import com.example.community.exception.QuestionException;
import com.example.community.mapper.QuestionMapper;
import com.example.community.service.NoticeService;
import com.example.community.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020
 * FileName: MyquestionController
 * Author:   wdy
 * Date:     2020/4/9 17:38
 * Description: 我的问题界面控制器
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionDtoService questionDtoService;

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/profile/{action}")
    public String myquestion(@PathVariable(name = "action") String action,
                             Model model,
                             HttpServletRequest request,
                             @RequestParam(name = "page",defaultValue = "1") Integer page,
                             @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            throw  new MyException(QuestionException.User_is_NUll);
        }
        Integer noticeCount = noticeService.getNoticeCount(Integer.valueOf(user.getAccountId()));
        if("question".equals(action)){
            model.addAttribute("saction","question");
            model.addAttribute("sactionName","我的问题");
            List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
            questionDtos = questionDtoService.listById(Integer.valueOf(user.getAccountId()),page,size);
            //System.out.println(questionDtos);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andCreatorEqualTo(Integer.valueOf(user.getAccountId()));
            Integer count = (int) questionMapper.countByExample(questionExample);
            //   System.out.println("count= "+count);s
            PageDto pagedto = new PageDto();
            pagedto.setPages(questionDtos);
            pagedto.setPage(page,count,size);
            model.addAttribute("pagedto",pagedto);
            model.addAttribute("count",count);
            model.addAttribute("noticeCount",noticeCount);
        }else if("repies".equals(action)){
            List<NoticeDto> noticeDtos = noticeService.findAllByUserId(Integer.valueOf(user.getAccountId()));
            int allNoticeCount = noticeService.getAllNoticeCount(Integer.valueOf(user.getAccountId()));
            PageDto pagedto = new PageDto();
            pagedto.setPages(noticeDtos);
            pagedto.setPage(page,allNoticeCount,size);
            model.addAttribute("pagedto",pagedto);
            model.addAttribute("noticeCount",noticeCount);
            model.addAttribute("allNoticeCount",allNoticeCount);
            model.addAttribute("saction","repies");
            model.addAttribute("sactionName","我的回复");
        }
        return "profile";
    }
}

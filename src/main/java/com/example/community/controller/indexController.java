package com.example.community.controller;

import com.example.community.demo.Question;
import com.example.community.demo.QuestionExample;
import com.example.community.demo.User;
import com.example.community.dto.PageDto;
import com.example.community.dto.QuestionDto;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.service.NoticeService;
import com.example.community.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/20 15:35
 * Modified By:
 */
@Controller
@CrossOrigin
public class indexController {

    @Autowired
    private QuestionDtoService questionDtoService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private NoticeService noticeService;
    /*
     * @Description:
     * @Author: weidongya
     * @Date: 2020/3/23 11:18
     * @param request
     * @result: String
     **/
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user =(User) request.getSession().getAttribute("user");
        if(user!=null) {
            Integer noticeCount = noticeService.getNoticeCount(Integer.valueOf(user.getAccountId()));
            model.addAttribute("noticeCount",noticeCount);
        }
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        questionDtos = questionDtoService.list(page,size);
        Integer count = (int) questionMapper.countByExample(new QuestionExample());
        PageDto pagedto = new PageDto();
        pagedto.setPages(questionDtos);
        pagedto.setPage(page,count,size);
        List<Question> viewMaxquestions = questionExtMapper.findViewMaxQuestion();
        model.addAttribute("viewMaxQuestions",viewMaxquestions);
        model.addAttribute("pagedto",pagedto);
        return "index";
    }

    @GetMapping("/searchquestioin")
    public String searchQuestion(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name="title") String title){
        User user =(User) request.getSession().getAttribute("user");
        if(user!=null) {
            Integer noticeCount = noticeService.getNoticeCount(Integer.valueOf(user.getAccountId()));
            model.addAttribute("noticeCount",noticeCount);
        }
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        questionDtos = questionDtoService.listByTitle(title,page,size);
        Integer count = (int) questionMapper.countByExample(new QuestionExample());
        PageDto pagedto = new PageDto();
        pagedto.setPages(questionDtos);
        pagedto.setPage(page,count,size);
        List<Question> viewMaxquestions = questionExtMapper.findViewMaxQuestion();
        model.addAttribute("viewMaxQuestions",viewMaxquestions);
        model.addAttribute("pagedto",pagedto);
        return "searchquestion";
    }
}

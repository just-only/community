package com.example.community.service;

import com.example.community.demo.Question;
import com.example.community.demo.QuestionExample;
import com.example.community.demo.User;
import com.example.community.demo.UserExample;
import com.example.community.dto.QuestionDto;
import com.example.community.exception.MyException;
import com.example.community.exception.QuestionExceptionMessage;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.example.community.service
 * Description：
 * Author: weidongya
 * Date:  2020/3/26 13:47
 * Modified By:
 */
@Service
public class  QuestionDtoService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;
    /*
     * @Description:查找所有的问题列表内容
     * @Author: weidongya
     * @Date: 2020/3/26 14:24
      * @param null
     * @result:
     **/
    public List<QuestionDto> list(Integer page,Integer size){
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        //    System.out.println(questions);
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        //   System.out.println(questions);
        for (Question question:questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(Integer.toString(question.getCreator()));
            //System.out.println(question);
            User user = userMapper.selectByExample(userExample).get(0);
            // System.out.println(user);
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            questionDto.setQuestion(question);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }
    public List<QuestionDto> listById(Integer userId,Integer page,Integer size){
        Integer offset = size*(page-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));

        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        //   System.out.println(questions);
        for (Question question:questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(Integer.toString(question.getCreator()));
            User user = userMapper.selectByExample(userExample).get(0);
            //System.out.println(user);
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            questionDto.setQuestion(question);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public QuestionDto findById(Integer id) {
        QuestionDto questionDto = new QuestionDto();
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new MyException(QuestionExceptionMessage.Question_No_Found);
        }
        questionDto.setQuestion(question);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(Integer.toString(question.getCreator()));
        User user =userMapper.selectByExample(userExample).get(0);
        questionDto.setUser(user);
        return questionDto;
    }

    public List<QuestionDto> listByTitle(String title,Integer page,Integer size){
        Integer offset = size*(page-1);
        List<Question> questions = questionExtMapper.findByTitle(title,offset,size);
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        //   System.out.println(questions);
        for (Question question:questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(Integer.toString(question.getCreator()));
            User user = userMapper.selectByExample(userExample).get(0);
            //System.out.println(user);
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            questionDto.setQuestion(question);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public void intViewCount(Integer id){
        Question question = questionMapper.selectByPrimaryKey(id);
        questionExtMapper.intView(question);
    }
}

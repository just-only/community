package com.example.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.community.demo.User;
import com.example.community.demo.UserExample;
import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import com.example.community.mapper.UserMapper;
import com.example.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Package: com.example.community.controller
 * Description：回调的控制器
 * Author: weidongya
 * Date:  2020/3/20 18:07
 * Modified By:
 */
@Controller
@CrossOrigin(origins = "https://github.com")
public class AutherizeController {

    //自动注入实体类，不用使用new来创建对象
    @Autowired
    private GithubProvider githubProvider;

    //value将配置文件中的内容进行填充
    //注意 $符号千万不能忘记
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Autowired
    private UserMapper userMapper;

    /*
     * @Description:
     * @Author: weidongya
     * @Date:  21:27
     * @param code 申请码
     * @param state 随机字符，用于防止伪造攻击
     * @result: String
     **/
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        //System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功，获取session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setCreateTime(System.currentTimeMillis());
            user.setModified(user.getCreateTime());
            user.setBio(githubUser.getBio());
            user.setImageUrl(githubUser.getAvatar_url());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()!=0){
                userExample.createCriteria().andIdEqualTo(users.get(0).getId());
                users.get(0).setCreateTime(null);
                 userMapper.updateByExampleSelective(users.get(0),userExample);
            }else{
                userMapper.insertSelective(user);
            }
            request.getSession().setAttribute("user",user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}

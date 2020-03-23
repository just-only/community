package com.example.community.controller;

import com.example.community.demo.User;
import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import com.example.community.mappr.UserMapper;
import com.example.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/20 18:07
 * Modified By:
 */
@Controller
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
    UserMapper userMapper;
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
                           HttpServletRequest request){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功，获取session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setAccunt_id(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setCreate_time(System.currentTimeMillis());
            user.setModified(user.getCreate_time());
            userMapper.insertUser(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }

}

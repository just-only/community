package com.example.community.controller;

import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import com.example.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/20 18:07
 * Modified By:
 */
@Controller
public class AutherizeController {

    @Autowired
    private GithubProvider githubProvider;

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
                           @RequestParam(name = "state") String state){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setClient_id("edad8c371426b2d36d73");
        accessTokenDto.setClient_secret("12a47962fe408bb7305855288fb486b733a9a9ab");
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.toString());
       return "index";
    }

}

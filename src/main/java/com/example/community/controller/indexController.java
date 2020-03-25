package com.example.community.controller;

import com.example.community.demo.User;
import com.example.community.mappr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/20 15:35
 * Modified By:
 */
@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;//自动注入userMapper

    /*
     * @Description:
     * @Author: weidongya
     * @Date: 2020/3/23 11:18
     * @param request
     * @result: String
     **/
    @GetMapping("/")
    public String index(HttpServletRequest request){

        if(request.getCookies()!=null) {

            Cookie[] cookies = request.getCookies();//获取cookies的所有内容
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {//存在name为token的数据，说明用户登陆过
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    request.getSession().setAttribute("user", user);//得到的用户信息，直接登录
                    break;
                }
            }
        }
        return "index";
    }
}

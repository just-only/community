package com.example.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Package: com.example.community.controller
 * Description：
 * Author: weidongya
 * Date:  2020/3/20 15:35
 * Modified By:
 */
@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}

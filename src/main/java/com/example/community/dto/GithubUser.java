package com.example.community.dto;

import lombok.Data;

/**
 * Package: com.example.community.dto
 * Description：github用户实例，在获取token令牌之后，封装用户数据
 * Author: weidongya
 * Date:  2020/3/20 20:35
 * Modified By:
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;
}

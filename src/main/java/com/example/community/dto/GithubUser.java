package com.example.community.dto;

/**
 * Package: com.example.community.dto
 * Description：github用户实例，在获取token令牌之后，封装用户数据
 * Author: weidongya
 * Date:  2020/3/20 20:35
 * Modified By:
 */
public class GithubUser {
    private Long id;
    private String name;
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

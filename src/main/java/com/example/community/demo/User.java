package com.example.community.demo;

import lombok.Data;

/**
 * Package: com.example.community.demo
 * Description：
 * Author: weidongya
 * Date:  2020/3/22 18:29
 * Modified By:
 */
@Data
public class User {
    private int id;
    private String account_id;
    private String name;
    private String token;
    private Long create_time;
    private Long modified;
    private String bio;
    private String image_url;
}

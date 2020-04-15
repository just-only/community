package com.example.community.demo;

import lombok.Data;

/**
 * Package: com.example.community.demo
 * Description：
 * Author: weidongya
 * Date:  2020/3/24 16:26
 * Modified By:
 */
@Data
public class Question {
    private Integer id;
    private String description;
    private String title;
    private Long create_time;
    private Long modified_time;
    private int comment_count;
    private int creator;
    private int view_count;
    private int like_count;
    private String tag;
}

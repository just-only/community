package com.example.community.JsonDto;

import lombok.Data;
import org.attoparser.dom.INestableNode;

/**
 * Copyright (C), 2018-2020
 * FileName: JsonComment
 * Author:   wdy
 * Date:     2020/4/28 16:17
 * Description: 有关评论的Json封装类
 */
@Data
public class JsonComment {
    private Integer questionId;
    private String commentText;
    private Integer parentId;
    private Integer commentor;
    private Integer type;
}

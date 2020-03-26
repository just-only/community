package com.example.community.dto;

import com.example.community.demo.Question;
import com.example.community.demo.User;
import lombok.Data;

/**
 * Package: com.example.community.dto
 * Description：
 * Author: weidongya
 * Date:  2020/3/26 13:45
 * Modified By:
 */
@Data
public class QuestionDto {
    private Question question;
    private User user;
}

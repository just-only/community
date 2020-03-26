package com.example.community.dto;

import lombok.Data;

/**
 * Package: com.example.community.dto
 * Description：AcceToken实例
 * Author: weidongya
 * Date:  2020/3/20 20:11
 * Modified By:
 */
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}

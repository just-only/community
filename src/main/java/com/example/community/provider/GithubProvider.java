package com.example.community.provider;

import com.alibaba.fastjson.JSON;
import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Package: com.example.community.provider
 * Description：githu的支持.根据code来获取令牌
 * Author: weidongya
 * Date:  2020/3/20 20:10
 * Modified By:
 */
@Component
public class GithubProvider {

    /*
     * @Description:
     * @Author: weidongya
     * @Date: 2020/3/20 21:26
     * @param accessTokenDto 根据申请码获取的令牌
     * @result: String
     **/
    public String getAccessToken(AccessTokenDto accessTokenDto){

       MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String str[] = string.split("&")[0].split("=");
                String token = str[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    /*
     * @Description:
     * @Author: weidongya
     * @Date: 21:26
     * @param accessTokenDto 根据申请码获取的令牌
     * @result: GithubUser  根据令牌获取的 github 用户
     **/
    public GithubUser getUser(String accessToken){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}

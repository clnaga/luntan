package com.emiyacc.luntan.provider;

import com.alibaba.fastjson.JSON;
import com.emiyacc.luntan.dto.AccessTokenDTO;
import com.emiyacc.luntan.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserDTO getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                // 由于版本更新，所以要换添加一个头
                .header("Authorization", "token " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(string, GithubUserDTO.class);
            // 如果登录不上去就自定义一个账号
            if (null == githubUserDTO.getId()) {
                Long tempId = 1L;
                githubUserDTO.setId(tempId);
            }
            if (null == githubUserDTO.getName()) {
                githubUserDTO.setName("Saber");
            }
            if (null == githubUserDTO.getBio()) {
                Long tempId = 1L;
                githubUserDTO.setBio("Saber my wife");
            }
            return githubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package life.maomao.community.provider;

import com.alibaba.fastjson.JSON;
import life.maomao.community.dto.AccessTokenDTO;
import life.maomao.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Maomao on 2020/6/15
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String urlStr = response.body().string();
            String[] urlStrArray = urlStr.split("&");
            String access_tokenStr = urlStrArray[0];
            String access_token = access_tokenStr.split("=")[1];
            return access_token;
        }catch (IOException e){ }
        return null;
    }

    public GithubUser getUser(String accessTokenDTO){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessTokenDTO)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

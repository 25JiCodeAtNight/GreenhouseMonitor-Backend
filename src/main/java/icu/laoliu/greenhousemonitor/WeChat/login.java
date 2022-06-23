package icu.laoliu.greenhousemonitor.WeChat;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.HTTP.HTTP;
import icu.laoliu.greenhousemonitor.WeChat.data.LoginRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class login {

    @GetMapping("/wechat/login")
    public String index(String code) {
        String APPID = "APPID";
        String SECRET = "SECRET";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String result = new HTTP(url).get();
        LoginRespondData resData = new Gson().fromJson(result, LoginRespondData.class);
        return resData.openid;
    }
}

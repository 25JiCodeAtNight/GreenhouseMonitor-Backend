package icu.laoliu.greenhousemonitor.web.v1.user;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.user.data.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class userRegister {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;
    String UID = new String();

    @PostMapping("/v1/user/userRegister")
    public String Get(@RequestBody String data) {
        // 解析请求体
        RegisterRequest request = new Gson().fromJson(data, RegisterRequest.class);
        String UID = UUID.randomUUID().toString().replaceAll("-", "");
        jdbcTemplate.update("INSERT INTO user(user_id,name,open_id) VALUES('" + UID + "','" + request.name + "','" + request.openID + "')");
        return UID;
    }
}

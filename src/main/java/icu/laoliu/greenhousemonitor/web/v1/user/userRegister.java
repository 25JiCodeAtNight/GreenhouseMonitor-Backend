package icu.laoliu.greenhousemonitor.web.v1.user;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.user.data.RegisterRequest;
import icu.laoliu.greenhousemonitor.web.v1.user.data.RegisterRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class userRegister {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;
    String UID = new String();

    @GetMapping("/v1/user/userRegister")
    public String Get(@RequestBody RegisterRequest request) {
        /*在数据库建立用户记录，并返回userID*/
//        UUID uuid=new UUID(20,4);
//        uuid = UUID.fromString(request.openID);
//        UID = uuid.toString();
//        sql = "" + greenhouseid;
        String UID = UUID.randomUUID().toString().replaceAll("-", "");
        jdbcTemplate.update("INSERT INTO user(user_id,name,open_id) VALUES('" + UID + "','" + request.name + "','" + request.openID + "')");
        RegisterRespond respond = new RegisterRespond();
        respond.userID = UID;
        String dataJson = new Gson().toJson(respond);
        return dataJson;
    }
    /*@GetMapping("/v1/user/register")
    public String Send() {
        RegisterRespond respond = new RegisterRespond();
        respond.userID = UID;
        String dataJson = new Gson().toJson(respond);
        return dataJson;
    }*/

//    @GetMapping("/v1/user/register")
//    public String Send() {
//        RegisterRespond respond = new RegisterRespond();
//        respond.userID = UID;
//        String dataJson = new Gson().toJson(respond);
//        return dataJson;
//    }

}

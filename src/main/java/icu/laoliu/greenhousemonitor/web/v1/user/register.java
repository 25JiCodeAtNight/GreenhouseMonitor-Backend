package icu.laoliu.greenhousemonitor.web.v1.user;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.RegisterRequest;
import icu.laoliu.greenhousemonitor.web.v1.user.data.RegisterRespond;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@RestController
public class register {
    String UID=new String();
    @GetMapping("/v1/user/register")
    public void Get(@RequestBody RegisterRequest request){
        /*在数据库建立用户记录，并返回userID*/
        UUID uuid=UUID.fromString(request.openID);
        UID=uuid.toString();
        DataBase dataBase =new DataBase();
        dataBase.setSQLString("SELECT "+UID +"FROM user");
        ResultSet resultSet= dataBase.haveReturnExecute();

        try {
            if(!resultSet.next()){
                dataBase.setSQLString("INSERT INTO user VALUES（"+UID +","+request.name+","+request.openID+"）");
                dataBase.noReturnExecute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/v1/user/register")
    public String Send(){
        RegisterRespond respond=new RegisterRespond();
        respond.userID=UID;
        String dataJson = new Gson().toJson(respond);
        return dataJson;
    }

}

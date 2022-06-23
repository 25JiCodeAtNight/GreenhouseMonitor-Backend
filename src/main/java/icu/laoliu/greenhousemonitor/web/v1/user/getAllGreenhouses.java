package icu.laoliu.greenhousemonitor.web.v1.user;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.Get_G_Respond;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class getAllGreenhouses {
    @GetMapping("/v1/user/getAllGreenhouses")
    public String Send(){
        Get_G_Respond respond=new Get_G_Respond();
        DataBase dataBase =new DataBase();
        dataBase.setSQLString("SELECT user.name,greenhouse.greenhouse_id FROM user,greenhouse WHERE user.user_id=greenhouse.user_id");
        ResultSet resultSet= dataBase.haveReturnExecute();
        while(true){
            try {
                if (!resultSet.next()) break;
                respond.add_Record(resultSet.getString(1),resultSet.getString(2));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        String dataJson = new Gson().toJson(respond.turn());
        return dataJson;
    }
}

package icu.laoliu.greenhousemonitor.web.v1.user;


import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.GetSRecord;
import icu.laoliu.greenhousemonitor.web.v1.user.data.GetSRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class getAllSensors {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;
    @GetMapping("/v1/user/getAllSensors")
    public String Send(){
//        GetSRespond respond=new GetSRespond();
//        DataBase dataBase =new DataBase();
//        dataBase.setSQLString("SELECT name,sensor_id FROM user,greenhouse,sensor WHERE user.user_id=greenhouse.user_id AND greenhouse.greenhouse_id=sensor.greenhouse_id");
//        ResultSet resultSet= dataBase.haveReturnExecute();
//        while(true){
//            try {
//                if (!resultSet.next()) break;
//                respond.add_Record(resultSet.getString(1),resultSet.getString(2));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
        sql = "SELECT name,sensor_id FROM user,greenhouse,sensor WHERE user.user_id=greenhouse.user_id AND greenhouse.greenhouse_id=sensor.greenhouse_id";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        GetSRespond respond=new GetSRespond();
        respond.responds=new GetSRecord[res.size()];
        for(int i = 0; i != res.size(); i++){

            
        }


        String dataJson = new Gson().toJson(respond.turn());
        return dataJson;

    }


}

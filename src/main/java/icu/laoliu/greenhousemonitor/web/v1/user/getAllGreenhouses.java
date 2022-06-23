package icu.laoliu.greenhousemonitor.web.v1.user;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.GetGRecord;
import icu.laoliu.greenhousemonitor.web.v1.user.data.GetGRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class getAllGreenhouses {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/user/getAllGreenhouses")
    public String Send(String userID) {
//        GetGRespond respond=new GetGRespond();
//        DataBase dataBase =new DataBase();
//        dataBase.setSQLString("SELECT user.name,greenhouse.greenhouse_id FROM user,greenhouse WHERE user.user_id=greenhouse.user_id");
//        ResultSet resultSet= dataBase.haveReturnExecute();
        sql = "SELECT greenhouse_name,greenhouse_id FROM user,greenhouse WHERE user.user_id=greenhouse.user_id AND user.user_id=" + "'" +userID + "'";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);

        GetGRespond respond = new GetGRespond();
        respond.responds = new GetGRecord[res.size()];
        for (int i = 0; i != res.size(); i++) {
            GetGRecord tmp = new GetGRecord();
            tmp.greenhouseID = (String) res.get(i).get("greenhouse_id");
            tmp.greenhouseName = (String) res.get(i).get("greenhouse_name");
            respond.responds[i] = tmp;
        }
        String dataJson = new Gson().toJson(respond);
        return dataJson;
    }
}

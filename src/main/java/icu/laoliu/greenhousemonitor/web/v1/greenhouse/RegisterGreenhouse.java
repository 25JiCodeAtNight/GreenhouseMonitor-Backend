package icu.laoliu.greenhousemonitor.web.v1.greenhouse;


import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.RegisterRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

@RestController
public class RegisterGreenhouse {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;
    @GetMapping("v1/greenhouse/register")
    public String  index(@RequestBody String respondDataString) {
        // 插入大棚
        RegisterRespondData respondData=new Gson().fromJson(respondDataString,RegisterRespondData.class);
        sql="select * from greenhouse";
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        int key=0;
        while(key==0)
        {
                List<Map<String,Object>> result=jdbcTemplate.queryForList(sql);
                if(result.isEmpty())
                {
                    key=1;
                    break;
                }
                for(int i=0;i<result.size();i++)
                {
                    String s1= (String) result.get(i).get("greenhouse_id");
                    if(uuid.equals(s1))
                    {
                        String temp=UUID.randomUUID().toString().replaceAll("-","");
                        uuid=temp;
                        key=0;
                        break;
                    }
                    else
                    {
                        key=1;
                    }
                }
        }
        int result1=jdbcTemplate.update("insert into greenhouse values ('"+uuid+"','"+respondData.name+"',"+respondData.position.latitude+","+respondData.position.longitude+","+respondData.userId+")");
        return uuid;
    }

}

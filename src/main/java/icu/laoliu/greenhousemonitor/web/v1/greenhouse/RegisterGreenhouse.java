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
    public void  index(@RequestBody String respondDataString) {
        // 插入大棚
        RegisterRespondData respondData=new Gson().fromJson(respondDataString,RegisterRespondData.class);
        sql="select greenhouse_id from greenhouse";
        UUID uuid=new UUID(20,1);
        int key=0;
        while(key==0)
        {
        while(true)
        {
                List<Map<String,Object>> result=jdbcTemplate.queryForList(sql);
                ListIterator listIterator=result.listIterator();
                if (!listIterator.hasNext()) break;
                String s1=listIterator.next().toString();
                if(uuid.equals(s1))
                {
                    UUID temp=new UUID(20,1);
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

        String sql2="insert into greenhouse values ("+uuid+","+respondData.name+","+respondData.position.latitude+","+respondData.position.longitude+","+respondData.userId+")";
        int result1=jdbcTemplate.update(sql2);

    }

}

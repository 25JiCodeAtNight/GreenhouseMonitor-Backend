package icu.laoliu.greenhousemonitor.web.v1.greenhouse;


import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.RegisterRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class RegisterGreenhouse {
    @GetMapping("v1/greenhouse/register")
    public void  index(@RequestBody RegisterRespondData respondData) {
        // 插入大棚
        DataBase dataBase=new DataBase();
        UUID uuid=new UUID(20,1);
        int key=0;
        while(key==0)
        {
        dataBase.setSQLString("select * from greenhouse ");
        ResultSet resultSet=dataBase.haveReturnExecute();
        while(true)
        {
            try {
                if (!resultSet.next()) break;
                String s1=resultSet.getString("greenhouse_id");
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
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

        }

        }

        dataBase.setSQLString("insert into greenhouse values ("+uuid+","+respondData.name+","+respondData.position.latitude+","+respondData.position.longitude+","+respondData.userId+")");
        dataBase.noReturnExecute();



    }

}

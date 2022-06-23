package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.RegisterRequestData;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.RegisterRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class sensorRegister {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/sensor/sensorRegister")
    public String index(@RequestBody String registerRequestData) {
        RegisterRequestData registerRequestData1 = new Gson().fromJson(registerRequestData, RegisterRequestData.class);
        int key = 0;
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        while (key == 0) {
            sql = "select * from sensor";
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
                for(int i=0;i<result.size();i++)
                {
                    String s1=(String) result.get(i).get("sensor_id");
                    if(s1.equals(uuid))
                    {
                        uuid=UUID.randomUUID().toString().replaceAll("-","");
                        key=0;
                        break;
                    }
                    else
                    {
                        key=1;
                    }
                }
        }
        sql = "insert into sensor values ('" + uuid + "','" + registerRequestData1.name + "'," + registerRequestData1.greenhouseID + ")";
        jdbcTemplate.update(sql);

        RegisterRespondData registerRespondData = new RegisterRespondData();
        registerRespondData.sensorID = uuid;
        String dataJson = new Gson().toJson(registerRespondData);
        return dataJson;
    }
}

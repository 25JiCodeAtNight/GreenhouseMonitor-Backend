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
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

@RestController
public class sensorRegister {
    public UUID uuid = new UUID(20, 1);
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("v1/sensor/register")
    public void receive(@RequestBody String registerRequestData) {
        RegisterRequestData registerRequestData1 = new Gson().fromJson(registerRequestData, RegisterRequestData.class);
        int key = 0;
        while (key == 0) {
            sql = "select sensor_id from sensor";
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            while (true) {
                    ListIterator listIterator = result.listIterator();
                    if (!listIterator.hasNext()) {
                        break;
                    }

                    String s1 = (String) listIterator.next();
                    if (uuid.equals(s1)) {
                        UUID temp = new UUID(20, 1);
                        uuid = temp;
                        key = 0;
                        break;
                    } else {
                        key = 1;
                    }
            }
        }
        sql = "insert into sensor values (" + uuid + "," + registerRequestData1.name + "," + registerRequestData1.greenhouseID + ")";
        jdbcTemplate.update(sql);
    }

    @GetMapping("v1/sensor/register")
    public String send() {
        RegisterRespondData registerRespondData = new RegisterRespondData();
        registerRespondData.sensorID = uuid.toString();
        String dataJson = new Gson().toJson(registerRespondData);
        return dataJson;
    }
}

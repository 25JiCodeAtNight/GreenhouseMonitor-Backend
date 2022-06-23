package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.DetailRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class sensorDetail {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/sensor/detail")
    public String index(String greenhouseid) {
        // 查询数据库，获得对应传感器信息
        sql = "SELECT sensor_record.temp,sensor_record.humidity,sensor_record.time FROM greenhouse, sensor, sensor_record WHERE greenhouse.greenhouse_id=sensor.greenhouse_id AND sensor.sensor_id=sensor_record.sensor_id AND greenhouse.greenhouse_id=" + "'" + greenhouseid + "'";
        List<Map<String, Object>> result1 = jdbcTemplate.queryForList(sql);
        Map<String, Object> result2 = jdbcTemplate.queryForMap(sql);
        Timestamp timetmp;
        float temptmp;
        float humitmp;
        timetmp = (Timestamp) result2.get("time");
        temptmp = (float) result2.get("temp");
        humitmp = (float) result2.get("humidity");

        DetailRespondData respondData = new DetailRespondData();
        respondData.details = new DetailRespondData.Detail[result1.size()];
        for (int i = 0; i < result1.size(); i++) {
            respondData.details[i] = respondData.new Detail();
            respondData.details[i].humi = humitmp;
            respondData.details[i].temp = temptmp;
            respondData.details[i].time = timetmp;
        }

        String dataJson = new Gson().toJson(respondData);
        return dataJson;
    }

}

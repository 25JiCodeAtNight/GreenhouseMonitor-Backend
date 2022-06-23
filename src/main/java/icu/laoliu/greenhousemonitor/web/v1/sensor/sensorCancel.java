package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.CancelRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class sensorCancel {

    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @PostMapping("v1/sensor/cancel")
    public void index(@RequestBody String reqData) throws IOException {
        CancelRequestData cancelRequestData1 = new Gson().fromJson(reqData, CancelRequestData.class);
        // Not good but...
        sql = "SELECT * FROM sensor WHERE sensor_id=" + "'" + cancelRequestData1.sensorID + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        if (!result.isEmpty()) {
            sql = "DELETE FROM sensor WHERE sensor_id=" + "'" + cancelRequestData1.sensorID + "'";
            jdbcTemplate.update(sql);
        }
    }
}

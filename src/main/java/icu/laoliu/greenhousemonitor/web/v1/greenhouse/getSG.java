package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class getSG {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/greenhouse/getSG")
    public long index(float minTemp, float maxTemp) {
        sql = "SELECT DISTINCT COUNT(*) count FROM greenhouse, sensor, sensor_record WHERE greenhouse.greenhouse_id=sensor.greenhouse_id AND sensor.sensor_id=sensor_record.sensor_id AND sensor_record.temp BETWEEN " + minTemp + " AND " + maxTemp;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        Long count = (Long)result.get("count");
        return count;
    }
}

package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.CancelRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class sensorCancel {

    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("v1/sensor/cancel")
    public void index(@RequestBody String reqData, HttpServletResponse response) throws IOException {
        CancelRequestData cancelRequestData1 = new Gson().fromJson(reqData, CancelRequestData.class);
        sql = "SELECT * FROM greenhouse WHERE greenhouse_id=" + cancelRequestData1.greenhouseID + " AND sensor_id=" + cancelRequestData1.sensorID;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        if(result.isEmpty()){
            response.setStatus(400);
            response.getWriter().append("您删除的不是您的传感器");
        }else{
            sql = "DELETE FROM greenhouse WHERE sensor_id=" + cancelRequestData1.sensorID + " AND greenhouse_id=" + cancelRequestData1.greenhouseID;
            jdbcTemplate.update(sql);
        }
    }
}

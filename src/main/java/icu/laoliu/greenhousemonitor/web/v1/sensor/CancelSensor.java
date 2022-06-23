package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.CancelRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancelSensor {
    @GetMapping("v1/sensor/cancel")
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    public void index(@RequestBody String reqData) {
        CancelRequestData cancelRequestData1 = new Gson().fromJson(reqData, CancelRequestData.class);
        sql = "SELECT * FROM greenhouse WHERE greenhouse_id=" + cancelRequestData.greenhouseID + " AND user_id=" + cancelRequestData.userID;
        dataBase.setSQLString("DELETE FROM greenhouse WHERE sensor_id=" + cancelRequestData1.sensorID + " AND greenhouse_id=" + cancelRequestData1.greenhouseID);
        dataBase.noReturnExecute();
    }
}

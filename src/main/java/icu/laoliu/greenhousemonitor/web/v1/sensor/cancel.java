package icu.laoliu.greenhousemonitor.web.v1.sensor;

import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.CancelRequestData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cancel {
    @GetMapping("/v1/sensor/cancel")
    public void index(@RequestBody CancelRequestData cancelRequestData) {
        DataBase dataBase = new DataBase();
        dataBase.setSQLString("DELETE FROM greenhouse WHERE sensor_id=" + cancelRequestData.sensorID + " AND greenhouse_id=" + cancelRequestData.greenhouseID);
        dataBase.noReturnExecute();
    }
}

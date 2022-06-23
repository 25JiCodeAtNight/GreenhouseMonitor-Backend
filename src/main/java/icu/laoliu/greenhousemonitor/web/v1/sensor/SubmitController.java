package icu.laoliu.greenhousemonitor.web.v1.sensor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.sql.Timestamp;

@RestController
public class SubmitController {
    @GetMapping("v1/sensor/submit")
    public void index(String sensorid, float humidity, float temperature) {
        Timestamp time = new Timestamp(new Date().getTime());
        System.out.println("SensorID:" + sensorid + "; Humidity:" + humidity + "; Temperature:" + temperature + "; time:" + time);
    }
}

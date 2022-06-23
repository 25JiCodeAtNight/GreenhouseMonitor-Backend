package icu.laoliu.greenhousemonitor.web.v1.sensor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmitController {
    @GetMapping("v1/sensor/submit")
    public void index(String sensorid, float humidity, float temperature) {
        System.out.println("SensorID:" + sensorid + "; Humidity:" + humidity + "; Temperature:" + temperature);
    }
}

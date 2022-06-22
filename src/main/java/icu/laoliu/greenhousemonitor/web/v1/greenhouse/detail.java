package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.DetailRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class detail {
    @GetMapping("v1/greenhouse/detail")
    public String index(String greenhouseID) {
        // 查询数据库，获得对应大棚的信息
        // 以下为测试数据
        DetailRespondData respondData = new DetailRespondData();
        respondData.name = "test";
        respondData.temp = 37.0F;
        respondData.humi = 10.0F;
        respondData.weather = "晴朗";
        respondData.cloud = 0;
        respondData.windspeed = 3;
        respondData.pressure = 1000;

        String dataJson = new Gson().toJson(respondData);
        return dataJson;
    }
}

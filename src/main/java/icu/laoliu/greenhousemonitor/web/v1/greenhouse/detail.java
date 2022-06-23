package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.DetailRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class detail {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;
    @GetMapping("v1/greenhouse/detail")
    public String index(String greenhouseid) {
        // 查询数据库，获得对应大棚的信息
        sql = "SELECT latitude,longitude,greenhouse_name FROM greenhouse WHERE greenhouse_id=" + greenhouseid;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);

        float latitude = 0, longitude = 0;
        String greenhouse_name = null;
        latitude = (float) result.get("latitude");
        longitude = (float) result.get("longitude");
        greenhouse_name = (String) result.get("greenhouse_name");

        //创建WeatherAPI类
        WeatherAPI weatherAPI = new WeatherAPI("TOKEN", latitude + "," + longitude);
        RealtimeWeatherData realtimeWeatherData = weatherAPI.getRealtimeWeather();
        float humitmp = realtimeWeatherData.now.humidity;
        int temptmp = realtimeWeatherData.now.temp;
        int cloudtmp = realtimeWeatherData.now.cloud;
        int windspeedtmp = realtimeWeatherData.now.windSpeed;
        int pressuretmp = realtimeWeatherData.now.pressure;
        String weathertmp = realtimeWeatherData.now.text;


        // 以下为测试数据
        DetailRespondData respondData = new DetailRespondData();
        respondData.name = greenhouse_name;
        respondData.temp = temptmp;
        respondData.humi = humitmp;
        respondData.weather = weathertmp;
        respondData.cloud = cloudtmp;
        respondData.windspeed = windspeedtmp;
        respondData.pressure = pressuretmp;

        String dataJson = new Gson().toJson(respondData);
        return dataJson;
    }
}

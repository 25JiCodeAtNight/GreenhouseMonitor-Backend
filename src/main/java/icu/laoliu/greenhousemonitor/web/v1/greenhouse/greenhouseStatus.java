package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.StatusRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class greenhouseStatus {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("v1/greenhouse/status")
    public String index(String greenhouseid) {
        // 查询数据库，获得对应大棚的信息
        sql = "SELECT latitude,longitude,greenhouse_name FROM greenhouse WHERE greenhouse_id='" + greenhouseid + "'";
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
        float temptmp = realtimeWeatherData.now.temp;
        String stattmp = realtimeWeatherData.now.text;

        StatusRespondData statusRespondData = new StatusRespondData();
        statusRespondData.name = greenhouse_name;
        statusRespondData.humidity = humitmp;
        statusRespondData.temp = temptmp;
        statusRespondData.stat = stattmp;
        statusRespondData.greenhouseID = greenhouseid;

        String dataJson = new Gson().toJson(statusRespondData);
        return dataJson;
    }
}

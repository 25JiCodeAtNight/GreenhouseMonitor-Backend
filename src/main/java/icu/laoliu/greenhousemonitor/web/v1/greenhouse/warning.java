package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.DisasterWarningData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.WarningRespondData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class warning {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("v1/greenhouse/warning")

    public String index(String greenhouseid) {

        sql = "SELECT latitude,longitude FROM greenhouse WHERE greenhouse_id=" + greenhouseid;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        float latitude = 0, longitude = 0;
        latitude = (float) result.get("latitude");
        longitude = (float) result.get("longitude");


        //创建WeatherAPI
        WeatherAPI weatherAPI = new WeatherAPI("TOKEN", latitude + "," + longitude);



        //创建WarningRespondData
        WarningRespondData warningRespondData = new WarningRespondData(weatherAPI.getDisasterWarning().warning.length);



        for (int i = 0; i < weatherAPI.getDisasterWarning().warning.length; i++) {
          warningRespondData.warningData[i].id = (weatherAPI.getDisasterWarning().warning[i].id);
          warningRespondData.warningData[i].name = weatherAPI.getDisasterWarning().warning[i].title;


        }

        String dataJson = new Gson().toJson(warningRespondData);
        return dataJson;

    }
}

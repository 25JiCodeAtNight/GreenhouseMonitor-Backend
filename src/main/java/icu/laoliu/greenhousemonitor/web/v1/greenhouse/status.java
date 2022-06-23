package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.StatusRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class status {
    @GetMapping("v1/greenhouse/status")
    public String index(String greenhouseid) {
        // 查询数据库，获得对应大棚的信息
        DataBase dataBase = new DataBase();
        dataBase.setSQLString("SELECT latitude,longitude,greenhouse_name FROM greenhouse WHERE greenhouse_id=" + greenhouseID);
        ResultSet resultSet = dataBase.haveReturnExecute();
        float latitude = 0, longitude = 0;
        String greenhouse_name = null;
        try {
            latitude = resultSet.getFloat("latitude");
            longitude = resultSet.getFloat("longitude");
            greenhouse_name = resultSet.getString("greenhouse_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        String dataJson = new Gson().toJson(statusRespondData);
        return dataJson;
    }
}

package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.DetailRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class detail {
    @GetMapping("/v1/greenhouse/detail")
    public String index(String greenhouseID) {
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
        WeatherAPI weatherAPI = new WeatherAPI("a69e5d82b8854aeaa71506c60413f45f", latitude + "," + longitude);
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

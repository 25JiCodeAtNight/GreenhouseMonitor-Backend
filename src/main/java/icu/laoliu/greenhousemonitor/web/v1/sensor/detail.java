package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.DetailRespondDataSensor;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class detail {
    @GetMapping("v1/sensor/detail")
    public String index(String sensorID) {
        // 查询数据库，获得对应大传感器信息
        DataBase dataBase = new DataBase();
        dataBase.setSQLString("SELECT greenhouse_id FROM sensor WHERE sensor_id=" + sensorID);
        ResultSet resultSet = dataBase.haveReturnExecute();
        float latitude = 0, longitude = 0;
        String greenhouse_id = null;
        try {
            greenhouse_id = resultSet.getString("greenhouse_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataBase.setSQLString("SELECT latitude,longitude FROM greenhouse WHERE greenhouse_id=" + greenhouse_id);
        resultSet = dataBase.haveReturnExecute();
        try {
            latitude = resultSet.getFloat("latitude");
            longitude = resultSet.getFloat("longitude");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //创建WeatherAPI类
        WeatherAPI weatherAPI = new WeatherAPI("a69e5d82b8854aeaa71506c60413f45f", latitude + "," + longitude);
        RealtimeWeatherData realtimeWeatherData = weatherAPI.getRealtimeWeather();
        String timetmp = realtimeWeatherData.now.obsTime;
        float humitmp = realtimeWeatherData.now.humidity;
        float temptmp = realtimeWeatherData.now.temp;

        // 以下为测试数据
        DetailRespondDataSensor respondData = new DetailRespondDataSensor();
        respondData.time = timetmp;
        respondData.temp = temptmp;
        respondData.humi = humitmp;

        String dataJson = new Gson().toJson(respondData);
        return dataJson;
    }

}

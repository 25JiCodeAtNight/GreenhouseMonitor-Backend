package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.DisasterWarningData;
import icu.laoliu.greenhousemonitor.WeatherAPI.WeatherAPI;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.WarningRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class warning {
    @GetMapping("v1/greenhouse/warning")
    public String index(String greenhouseid) {
        DataBase dataBase = new DataBase();
        dataBase.setSQLString("SELECT latitude,longitude FROM greenhouse WHERE greenhouse_id=" + greenhouseid);
        ResultSet resultSet = dataBase.haveReturnExecute();
        float latitude = 0, longitude = 0;
        try {
            latitude = resultSet.getFloat("latitude");
            longitude = resultSet.getFloat("longitude");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //创建WeatherAPI
        WeatherAPI weatherAPI = new WeatherAPI("a69e5d82b8854aeaa71506c60413f45f", latitude + "," + longitude);
        DisasterWarningData disasterWarningData = weatherAPI.getDisasterWarning();

        //创建WarningRespondData
        WarningRespondData warningRespondData = new WarningRespondData();
        warningRespondData.warningData = new WarningRespondData.WarningData[disasterWarningData.warning.length];
        for (int i = 0; i < disasterWarningData.warning.length; i++) {
            warningRespondData.warningData[i].id = disasterWarningData.code;
            warningRespondData.warningData[i].name = disasterWarningData.warning[i];
        }

        String dataJson = new Gson().toJson(warningRespondData);
        return dataJson;

    }
}

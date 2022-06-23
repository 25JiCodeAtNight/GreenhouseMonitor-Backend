package icu.laoliu.greenhousemonitor.web.v1.sensor;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.RegisterRequestData;
import icu.laoliu.greenhousemonitor.web.v1.sensor.data.RegisterRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class RegisterSensor {
    public UUID uuid = new UUID(20, 1);
    @GetMapping("v1/sensor/register")
    public void receive(@RequestBody RegisterRequestData registerRequestData) {
        RegisterRespondData
        DataBase dataBase = new DataBase();
        int key = 0;
        while (key == 0) {
            dataBase.setSQLString("select * from sensor");
            ResultSet resultSet = dataBase.haveReturnExecute();
            while (true) {
                try {
                    if (!resultSet.next()) {
                        break;
                    }
                    String s1 = resultSet.getString("sensor_id");
                    if (uuid.equals(s1)) {
                        UUID temp = new UUID(20, 1);
                        uuid = temp;
                        break;
                    } else {
                        key = 1;
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        dataBase.setSQLString("insert into sensor values ("+uuid+","+registerRequestData.name+","+registerRequestData.greenhouseID+")");
        dataBase.noReturnExecute();
    }

    @GetMapping("v1/sensor/register")
    public String send(){
        RegisterRespondData registerRespondData = new RegisterRespondData();
        registerRespondData.sensorID = uuid.toString();
        String dataJson = new Gson().toJson(registerRespondData);
        return dataJson;
    }
}

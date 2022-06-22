package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.CancelRequestData;
import org.springframework.web.bind.annotation.*;

@RestController
public class cancel {
    @GetMapping("/v1/greenhouse/cancel")
    public void index(@RequestBody CancelRequestData cancelRequestData) {
        DataBase dataBase = new DataBase();
        dataBase.setSQLString("DELETE FROM greenhouse WHERE greenhouse_id=" + cancelRequestData.greenhouseID + " AND user_id=" + cancelRequestData.userID);
        dataBase.noReturnExecute();
    }
}

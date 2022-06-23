package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.CancelRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class greenhouseCancel {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @PostMapping("v1/greenhouse/cancel")
    public void index(@RequestBody String cancelRequestData) throws IOException {
        CancelRequestData cancelRequestData1 = new Gson().fromJson(cancelRequestData, CancelRequestData.class);
        sql = "DELETE FROM greenhouse WHERE greenhouse_id=" + "'" + cancelRequestData1.greenhouseID + "'" + " AND user_id=" + "'" + cancelRequestData1.userID + "'";
        jdbcTemplate.update(sql);
    }
}

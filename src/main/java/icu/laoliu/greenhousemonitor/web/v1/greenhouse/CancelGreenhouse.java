package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.CancelRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class CancelGreenhouse {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("v1/greenhouse/cancel")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(@RequestBody CancelRequestData cancelRequestData, HttpServletResponse response) throws IOException {

        sql = "SELECT * FROM greenhouse WHERE greenhouse_id=" + cancelRequestData.greenhouseID + " AND user_id=" + cancelRequestData.userID;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        if (result.isEmpty()) {
            response.setStatus(400);
            response.getWriter().append("您删除的不是您的大棚");
        }else{
            sql = "DELETE FROM greenhouse WHERE greenhouse_id=" + cancelRequestData.greenhouseID + " AND user_id=" + cancelRequestData.userID;
            jdbcTemplate.update(sql);
        }

    }
}

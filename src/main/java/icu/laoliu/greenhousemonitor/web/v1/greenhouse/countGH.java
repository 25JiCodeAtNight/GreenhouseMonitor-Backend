package icu.laoliu.greenhousemonitor.web.v1.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class countGH {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/greenhouse/countGH")
    public long index(String userID) {
        sql = "SELECT COUNT(*) count FROM greenhouse WHERE user_id='" + userID + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        long count = (Long) result.get("count");
        return count;
    }
}

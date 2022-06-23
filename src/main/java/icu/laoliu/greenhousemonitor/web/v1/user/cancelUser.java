package icu.laoliu.greenhousemonitor.web.v1.user;

import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.CancelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 url: v1/user/cancel
Request:
{
"openID": string,
}
Respond:
200 OK

 */
@RestController
public class cancelUser {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/v1/user/cancel")
    public void Get(@RequestBody CancelRequest request) {
        jdbcTemplate.update("DELETE FROM user WHERE open_id=" + request.openID);
    }
}

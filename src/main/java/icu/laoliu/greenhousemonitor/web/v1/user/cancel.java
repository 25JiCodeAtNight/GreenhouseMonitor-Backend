package icu.laoliu.greenhousemonitor.web.v1.user;

import icu.laoliu.greenhousemonitor.DB.DataBase;
import icu.laoliu.greenhousemonitor.web.v1.user.data.Cancel_Request;
import org.springframework.web.bind.annotation.PostMapping;
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
public class cancel {
    @PostMapping("/v1/user/cancel")
    public void Get(@RequestBody Cancel_Request request){
        DataBase dataBase =new DataBase();
        dataBase.setSQLString("DELETE FROM user WHERE open_id="+request.openID);
        dataBase.noReturnExecute();
    }
}

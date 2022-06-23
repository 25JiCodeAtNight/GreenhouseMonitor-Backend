package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.knowledgeBase.data.getColumnsReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class getColumns {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/knowledgeBase/getColumns")
    public String getColumn(){

        sql = "select column_name,column_id from columns";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        getColumnsReturn data = new getColumnsReturn(res.size());
        for (int i = 0; i < res.size(); i++) {
            data.columns[i].columnName = (String) res.get(i).get("column_name");
            data.columns[i].columnId = (String) res.get(i).get("column_id");
        }

        String dataJson = new Gson().toJson(data);
        return dataJson;
    }

}

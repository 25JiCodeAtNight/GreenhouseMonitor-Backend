package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.knowledgeBase.data.getArticleReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class getArticles {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;

    @GetMapping("/v1/knowledgeBase/getArticles")
    public String getArticles(int columnId){

        String column_id = Integer.toString(columnId);

        sql = "select article_id,article_name,discription from article where column_id = "+column_id;
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        getArticleReturn data = new getArticleReturn(res.size());
        for (int i = 0; i < res.size(); i++) {
            data.articles[i].ArticleId = (String) res.get(i).get("article_id");
            data.articles[i].ArticleTitle = (String) res.get(i).get("article_name");
            data.articles[i].ArticleDiscription = (String) res.get(i).get("discription");
        }
        String dataJson = new Gson().toJson(data);
        return dataJson;

    }

}

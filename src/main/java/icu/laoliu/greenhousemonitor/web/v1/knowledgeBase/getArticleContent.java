package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.knowledgeBase.data.getArticleContentReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class getArticleContent {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String sql;


    @GetMapping("/v1/knowledgeBase/getArticleContent")
    public String getArticleContent(String articleId){
        sql = "select article_id,article_text from article where article_id = "+articleId;
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        getArticleContentReturn data = new getArticleContentReturn();
        data.articleid = (String) res.get(0).get("article_id");
        data.articleid = (String) res.get(0).get("article_text");

        String dataJson = new Gson().toJson(data);
        return dataJson;
    }

}

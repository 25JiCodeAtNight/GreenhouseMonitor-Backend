package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase.data;

import org.apache.maven.artifact.Artifact;

public class getArticleReturn {
    public String columnId;
    public Article[] articles;

    public getArticleReturn(int a){
        articles = new Article[a];
        for (int i = 0; i < a; i++) {
            articles[i] = new Article();
        }
    }

    public class Article{
        public String ArticleId;
        public String ArticleTitle;
        public  String ArticleDiscription;

    }
}

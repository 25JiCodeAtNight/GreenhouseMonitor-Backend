package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase.data;

public class getColumnsReturn {
    public Column[] columns;

    public getColumnsReturn(int a){
        columns = new Column[a];
        for (int i = 0; i < a; i++) {
            columns[i] = new Column();
        }
    }
    public class  Column{
        public String  columnName;
        public String  columnId;
    }
}



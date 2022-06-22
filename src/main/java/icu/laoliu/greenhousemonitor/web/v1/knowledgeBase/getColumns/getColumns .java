package icu.laoliu.greenhousemonitor.web.v1.knowledgeBase;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.web.v1.greenhouse.data.DetailRespondData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class getColumn{
    @GetMapping('v1/knowledgeBase/getColumns')

    public String index(){

        DetailRespondData respondData = new DetailRespondData();
        //连接到数据库上
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1、注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //2、获取连接
            connection = DriverManager.getConnection("jdbc:mysql://170.178.195.149:3306/ghmonitor","GHMonitor","TTdFEs4TEfkpNLXz");
            //3、获取数据库操作对象
            statement = connection.createStatement();
            //4.设置sql操作语句
            String sql = "select distinct columnName,columnId from columns";
            //5.执行语句
            resultSet = statement.executeQuery(sql);
            //6、处理查询结果集
            while (resultSet.next()){
                respondData.columnName = insert(respondData.columnName,resultSet.getString("columnName"));
                respondData.columnId = insert(respondData.columnId,resultSet.getString("columnId"));
            }catch (SQLException e) {
                e.printStackTrace();
            }finally { //7、关闭连接
                if(resultSet!=null)
                {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(statement!=null)
                {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            String dataJson = new Gson().toJson(respondData);
            return dataJson;


    }

}
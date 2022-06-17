package icu.laoliu.greenhousemonitor.DB;

import java.sql.*;

    public class InterConnectionAPI {
        public final String DATA_BASE_URL = "jdbc:mysql://IPADDRESS/ghmonitor?useUnicode = true & characterEncoding = utf-8 & useSSL = false";
        public final String DATA_BASE_USERNAME = "USERNAME";
        public final String DATA_BASE_PASSWORD = "PASSWORD";
        public String SQLString;
        public Connection connection;


        public InterConnectionAPI() {                       //无参构造InterConnectionAPI对象
            try {
                connection = DriverManager.getConnection(DATA_BASE_URL, DATA_BASE_USERNAME, DATA_BASE_PASSWORD);
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }


        public void setSQLString(String sqlString) {       //传入一个SQL语句，放入InterConnectionAPI对象中
            SQLString = sqlString;
        }


        public void stopConnection() {                     //关闭connection的函数
            try {
                connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }


        public void noReturnExecute() {                    //没有返回值的操作
            try {
                Statement start = connection.createStatement();
                start.executeUpdate(SQLString);

            } catch (SQLException e) {
                e.getStackTrace();
                System.out.println("报错SQLException，没有更新成功");
            }
        }


        public ResultSet haveReturnExecute() {            //有返回值的操作
            ResultSet resultSet;

            try {
                Statement start = connection.createStatement();
                resultSet = start.executeQuery(SQLString);
                return resultSet;

            } catch (NullPointerException e) {
                System.out.println(e.getStackTrace());
                System.out.println("报错NullPointerException");
                return null;

            }catch (SQLException e){
                System.out.println(e.getStackTrace());
                System.out.println("报错SQLException");
                return null;
            }
        }
    }



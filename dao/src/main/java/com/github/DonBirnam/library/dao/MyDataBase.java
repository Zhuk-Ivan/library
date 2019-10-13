package com.github.DonBirnam.library.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyDataBase {
    public Connection connect() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");
        return DriverManager.getConnection(url,user,password);
    }

    public static MyDataBase getInstance() {
        return MyDataBase.SingletonHolder.HOLDER_INSTANCE;
    }



    private static class SingletonHolder {
        static final MyDataBase HOLDER_INSTANCE = new MyDataBase();
    }
}

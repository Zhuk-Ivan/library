package com.github.DonBirnam.library.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyDataBase {

    private final ComboPooledDataSource pool;

    private MyDataBase(){
     try{
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    pool = new ComboPooledDataSource();
    ResourceBundle resource = ResourceBundle.getBundle("db");
    String url = resource.getString("url");
    String user = resource.getString("user");
    String password = resource.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(password);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(100);
}
    public Connection connect(){
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MyDataBase getInstance() {
        return MyDataBase.SingletonHolder.HOLDER_INSTANCE;
    }



    private static class SingletonHolder {
        static final MyDataBase HOLDER_INSTANCE = new MyDataBase();
    }
}

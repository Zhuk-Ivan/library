package com.github.DonBirnam.dao;

import com.github.DonBirnam.library.dao.MyDataBase;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyDataBaseTest {

    @Test
    void getInstance() {
        MyDataBase myDataBase = MyDataBase.getInstance();
        assertNotNull(myDataBase);
    }

    @Test
    void connect() {
        MyDataBase myDataBase=MyDataBase.getInstance();
        Connection connection=myDataBase.connect();
        assertNotNull(connection);
    }

}

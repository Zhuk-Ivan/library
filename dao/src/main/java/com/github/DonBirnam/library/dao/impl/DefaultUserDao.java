package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.MyDataBase;
import com.github.DonBirnam.library.dao.UserDao;
import com.github.DonBirnam.library.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void saveUser(User user) {
        final String sql = "insert into user(first_name, last_name, phone, email,role,login,password) values(?,?,?,?,?,?,?)";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getLogin());
            ps.setString(7, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to save user", e);
        }
    }

    @Override
    public void deleteUser(String login) {
        final String sql = "delete from user where login = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Unable to delete user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "select * from user where role='user' or role ='blocked'";
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    user = new User(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("role"));
                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("There are no users in data base");
        return users;
    }

    @Override
    public void changePersonalData(User user) {
        final String sql = "update user set first_name=?, last_name=?, phone=?, email=?, password=? where id=?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());

            ps.setLong(6,user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to update user", e);
        }
    }

    @Override
    public void blockUser(String role, Long id) {
        final String sql = "update user set role=? where id = ?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to block user", e);
        }
    }


    @Override
    public User showUser(String login) {
        final String sql = "select * from user where login = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("role"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Unable to show user", e);
        }

        return null;
    }
}

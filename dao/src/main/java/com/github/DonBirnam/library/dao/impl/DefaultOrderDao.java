package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.MyDataBase;
import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao implements OrderDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        final static OrderDao HOLDER_INSTANCE = new DefaultOrderDao();
    }

    public static OrderDao getInstance(){
        return DefaultOrderDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void createOrder(Order order) {
        final String sql = "insert into orders(book_id, user_id, take_date, expire_date) values(?,?,?,?)";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, order.getBookId());
            ps.setLong(2, order.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(order.getTakeDate()));
            ps.setTimestamp(4, Timestamp.valueOf(order.getExpireDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to save order", e);
        }

    }


        @Override
    public Order findById(Long id) {
            final String sql = "select * from orders where id = ?";

            try (Connection connection = MyDataBase.getInstance().connect();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Order(
                                rs.getLong("id"),
                                rs.getLong("book_id"),
                                rs.getLong("user_id"),
                                rs.getTimestamp("take_date").toLocalDateTime(),
                                rs.getTimestamp("expire_date").toLocalDateTime());
                    } else {
                        return null;
                    }
                }
            } catch (SQLException e) {
                logger.error("Unable to find order by id", e);
            }

            return null;
    }

    @Override
    public void updateOrder(Order order) {
        final String sql = "update orders set book_id=?,user_id=?, take_date=?,expire_date=? where id = ?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, order.getBookId());
            ps.setLong(2, order.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(order.getTakeDate()));
            ps.setTimestamp(4, Timestamp.valueOf(order.getExpireDate()));

            ps.setLong(5, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to update order", e);
        }

    }

    @Override
    public void deleteOrder(Long id) {
        final String sql = "delete from orders where id = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Unable to delete order", e);
        }

    }

    @Override
    public List<Order> getAllOrders() {
        final String sql = "select * from orders";
        List<Order> orders = new ArrayList<>();
        Order order;
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    order = new Order(
                            rs.getLong("id"),
                            rs.getLong("book_id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("take_date").toLocalDateTime(),
                            rs.getTimestamp("expire_date").toLocalDateTime());
                    orders.add(order);
                }
                return orders;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("There are no orders in data base");
        return orders;
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        final String sql = "select * from orders where user_id=?";
        List<Order> orders = new ArrayList<>();
        Order order;
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    order = new Order(
                            rs.getLong("id"),
                            rs.getLong("book_id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("take_date").toLocalDateTime(),
                            rs.getTimestamp("expire_date").toLocalDateTime());
                    orders.add(order);
                }
                return orders;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("There are no orders in data base");
        return orders;
    }
}


